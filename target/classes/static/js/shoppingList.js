function getShoppingLists(tableId) {
    let table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/shopping/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                let row = table.insertRow(table.rows.length);

                //insert td for first column
                var cell1 = row.insertCell(0);
                cell1.style.width = '20%';
                cell1.style.height = '20px';
                cell1.innerHTML = entry[1].shoppingListID;

                //insert td for 2nd column and so on 
                var cell2 = row.insertCell(1);
                cell2.style.width = '20%';
                cell2.style.height = '20px';
                cell2.innerHTML = entry[1].username;

                var cell3 = row.insertCell(2);
                cell3.style.width = '20%';
                cell3.style.height = '20px';
                cell3.innerHTML = entry[1].name;


                var cell0 = row.insertCell(3);
                cell0.style.width = '5%';
                cell0.style.height = '20px';
                if(entry[1].username == activeUsername) {
                    var checkBox = '<input class="deleteCheckbox" type="checkbox">';
                    cell0.innerHTML = checkBox;
                }

                //insert td for the radio button
                var radioButton = `<input value="${entry[1].shoppingListID}" type="radio" name="selected_shoppingList" onclick="getDesirables('dataTableDesirable')">`; //create a checkbox
                var cell5 = row.insertCell(-1);
                cell5.style.width = '5%';
                cell5.style.height = '20px';
                cell5.innerHTML = radioButton;
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function createShoppingList(tableId) {
    let table = document.getElementById(tableId);
    let name = document.getElementById('shoppingListName').value;

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/shopping/create?accountID=${accountID}&name=${name}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            getShoppingLists(tableId);
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

function getDesirables(tableId) {
    var table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";

    let shoppingListID = document.querySelector('input[name="selected_shoppingList"]:checked');

    if(shoppingListID != null) {
        let owner = shoppingListID.parentNode.parentNode.cells[1].innerText;
        if(activeUsername == owner) {
            toggleAddDesirable(false);
        } else {
            toggleAddDesirable(true);
        }

        shoppingListID = shoppingListID.value;
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/desirables/retrieve/shopping?shoppingListID=${shoppingListID}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                let response = JSON.parse(this.responseText);   

                Object.entries(response).forEach((entry) => {
                    let row = table.insertRow(table.rows.length);

                    if(entry[1].checked == true) {
                        row.className = "strikeout";
                    } else {
                        row.className = "";
                    }

                    var cell1 = row.insertCell(0);
                    cell1.style.width = '20%';
                    cell1.style.height = '20px';
                    cell1.innerHTML = entry[1].desirableID;

                    var cell2 = row.insertCell(1);
                    cell2.style.width = '20%';
                    cell2.style.height = '20px';
                    cell2.innerHTML = entry[1].name;
                
                    var cell3 = row.insertCell(2);
                    cell3.style.width = '20%';
                    cell3.style.height = '20px';
                    cell3.innerHTML = entry[1].store;

                    var cell4 = row.insertCell(3);
                    cell4.style.width = '20%';
                    cell4.style.height = '20px';
                    cell4.innerHTML = entry[1].quantity;

                    var cell5 = row.insertCell(4);
                    cell5.style.width = '20%';
                    cell5.style.height = '20px';

                    var cell6 = row.insertCell(5);
                    cell6.style.width = '20%';
                    cell6.style.height = '20px';

                    let unitPrice = "N/A";
                    let totalPrice = "N/A";
                    if(entry[1].price >= 0) {
                        unitPrice = `\$${entry[1].price.toFixed(2)}`;
                        totalPrice = `\$${(entry[1].price * entry[1].quantity).toFixed(2)}`;
                    }
                    cell5.innerHTML = unitPrice;
                    cell6.innerHTML = totalPrice;
    
                    //insert td for the checkbox
                    var checkbox = '<input class="deleteCheckbox" type="checkbox">';//create a checkbox
                    var cell0 = row.insertCell(6);
                    cell0.style.width = '5%';
                    cell0.style.height = '20px';
                    cell0.innerHTML = checkbox;//put it inside the cell
                });
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } else {
        toggleAddDesirable(true);
    }
}

function addDesirable(tableId) {
    let table = document.getElementById(tableId);

    let shoppingListID = document.querySelector('input[name="selected_shoppingList"]:checked');
    
    if(shoppingListID != null) {
        shoppingListID = shoppingListID.value;
        let itemID = document.getElementById('desirableSelect').value;
        let storeID = document.getElementById('desirableStoreSelect').value;
        let quantity = document.getElementById('desirableQuantity').value;
    
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/desirables/create?accountID=${accountID}&shoppingListID=${shoppingListID}&storeID=${storeID}&itemID=${itemID}&quantity=${quantity}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                getDesirables(tableId);
            }
        };
        xmlhttp.open("POST", url, true);
        xmlhttp.send(); 
    }
}

function toggleAddDesirable(disabled) {
    document.getElementById('desirableSelect').disabled = disabled;
    document.getElementById('desirableStoreSelect').disabled = disabled;
    document.getElementById('desirableQuantity').disabled = disabled;
    document.getElementById('addDesirableBtn').disabled = disabled;
    document.getElementById('deleteDesirableBtn').disabled = disabled;
}

function getShoppingListItemList(selectId) {
    let select = document.getElementById(selectId);
    select.innerHTML = '<option value="" disabled selected hidden></option>';

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/items/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                let option = document.createElement("option");
                option.value = entry[1].itemID;
                option.text = entry[1].name;
                select.add(option);
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function getShoppingListStoreList(selectId) {
    let select = document.getElementById(selectId);
    select.innerHTML = '<option value="" disabled selected hidden></option>';

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/stores/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                let option = document.createElement("option");
                option.value = entry[1].storeID;
                option.text = entry[1].name;
                select.add(option);
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function getShoppingListShareList(selectId) {
    let select = document.getElementById(selectId);
    select.innerHTML = '<option value="" disabled selected hidden></option>';

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/shopping/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                if(entry[1].username == activeUsername) {
                    let option = document.createElement("option");
                    option.value = entry[1].shoppingListID;
                    option.text = entry[1].name;
                    select.add(option);
                }
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function addShoppingListShare(tableId) {
    let table = document.getElementById(tableId);

    let shareeName = document.getElementById('shoppingListAccountName').value;
    let shoppingListID = document.getElementById('shoppingListShareSelect').value;

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/shares/create?accountID=${accountID}&subjectID=${shoppingListID}&shareeName=${shareeName}&type=shoppingList`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            getShares(tableId, 'shoppingList');
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

function toggleDesirableStrikeout(tableId) {
    let table = document.getElementById(tableId);
    let checkboxes = table.querySelectorAll('input[type="checkbox"]:checked'); //finds all checked checkboxes in the table

    Object.entries(checkboxes).forEach((entry) => {
        let xmlhttp = new XMLHttpRequest();

        let tr = entry[1].parentElement.parentElement;
        let desirableID = tr.cells[0].innerText;
        let path = "";
        if(tr.className != "strikeout") {
            path = "check";
            tr.className = "strikeout";
        } else {
            path = "uncheck";
            tr.className = "";
        }

        let url = `http://127.0.0.1:8080/desirables/${path}?desirableID=${desirableID}`;
        xmlhttp.open("PUT", url, true);
        xmlhttp.send();
    });
}