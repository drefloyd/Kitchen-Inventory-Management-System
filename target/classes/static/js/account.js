function createAccount(labelId) {
    let statusLabel = document.getElementById(labelId);

    if(accountID == null) {
        let username = document.getElementById('userNameEnt').value;
        let password = document.getElementById('passwordEnt').value;
    
        if (username.indexOf(' ') >= 0) {
            statusLabel.innerHTML = "Failure: Username should not contain spaces!";
        } else if (password.length < 8 || password.indexOf(' ') >= 0) {
            statusLabel.innerHTML = "Failure: Password is required to be at least 8 characters and should not contain spaces!";
        } else {
            let xmlhttp = new XMLHttpRequest();
            let url = `http://127.0.0.1:8080/accounts/create?username=${username}&password=${password}`;
            xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4) {
                    if(this.status == 200) {
                        statusLabel.innerText = "Success: Account has been created! Please login to access the account!";
                    } else {
                        statusLabel.innerText = "Failure: Account could not be created!";
                    }
                }
            };
            xmlhttp.open("POST", url, true);
            xmlhttp.send();
        }
    } else {
        statusLabel.innerHTML = `Failure: ${activeUsername} is logged in! Log out to create an account!`;
    }
}

function login(labelID) {
    let statusLabel = document.getElementById(labelID);
    if(accountID == null) {
        let username = document.getElementById('userNameEnt').value;
        let password = document.getElementById('passwordEnt').value;
    
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/accounts/login?username=${username}&password=${password}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4) {
                if(this.status == 200) {
                    statusLabel.innerText = "Success: Login is successful! The tabs have been unlocked!";
                    let response = JSON.parse(this.responseText);
                    accountID = response.accountID;
                    activeUsername = response.username;
                    toggleTabs(false);
                } else {
                    statusLabel.innerText = "Failure: Login has failed! Please try to login again!";
                }
            }
        };
        xmlhttp.open("PUT", url, true);
        xmlhttp.send();
    } else {
        statusLabel.innerHTML = `Failure: Already logged in as ${activeUsername}!`;
    }

}

function logout(labelId) {
    let statusLabel = document.getElementById(labelId);
    if(accountID != null) {
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/accounts/logout?accountID=${accountID}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                toggleTabs(true);
                accountID = null;
                activeUsername = null;
                statusLabel.innerHTML = "Success: Logout is successful!";
            }
        };
        xmlhttp.open("PUT", url, true);
        xmlhttp.send();
    } else {
        statusLabel.innerHTML = "Failure: Already logged out!";
    }
}

function changePassword(labelId) {
    var statusLabel = document.getElementById(labelId);

    var oldPassword = document.getElementById('OldPass').value;
    var newPassword = document.getElementById('NewPass').value;
    var confirmPassword = document.getElementById('NewPassAgain').value;

    if (newPassword.localeCompare(confirmPassword) != 0) {
        statusLabel.innerHTML = "Failure: New password and confirmation password are different!";
    } else if (newPassword.length < 8 && newPassword.indexOf(' ') < 0) {
        statusLabel.innerHTML = "Failure: New Password is required to be at least 8 characters and should not contain spaces!";
    } else if (newPassword.localeCompare(oldPassword) == 0) {
        statusLabel.innerHTML = "Failure: New password must be different from the old password!";
    } else {
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/accounts/password/update?accountID=${accountID}&oldPassword=${oldPassword}&newPassword=${newPassword}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4) {
                if(this.status == 200) {
                    statusLabel.innerText = "Success: Password has been changed successfully!";
                } else {
                    statusLabel.innerText = "Failure: Old password is incorrect!";
                }
            }
        };
        xmlhttp.open("PUT", url, true);
        xmlhttp.send();
    }
}

function deleteAccount(labelId) {
    let statusLabel = document.getElementById(labelId);

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/accounts/delete?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("loginTabButton").click();
            toggleTabs(true);
            accountID = null;
            statusLabel.innerHTML = "Success: Account has been deleted successfully!";
        }
    };
    xmlhttp.open("DELETE", url, true);
    xmlhttp.send();
}