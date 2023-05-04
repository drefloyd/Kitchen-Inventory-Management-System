function getInventories(tableId) {
    let table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/inventories/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                let row = table.insertRow(table.rows.length);

                //insert td for first column
                var cell1 = row.insertCell(0);
                cell1.style.width = '20%';
                cell1.style.height = '20px';
                cell1.innerHTML = entry[1].inventoryID;

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
                    var checkBox = '<input class="deleteCheckbox" type="checkbox">';//create a checkbox
                    cell0.innerHTML = checkBox;
                }

                //insert td for the radio button
                var radioButton = `<input value="${entry[1].inventoryID}" type="radio" name="selected_inventory" onclick="getStocks('dataInvItems')">`; //create a checkbox
                var cell5 = row.insertCell(-1);
                cell5.style.width = '5%';
                cell5.style.height = '20px';
                cell5.innerHTML = radioButton;//put it inside the cell
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function createInventory(tableId) {
    let table = document.getElementById(tableId);
    let name = document.getElementById('invName').value;

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/inventories/create?accountID=${accountID}&name=${name}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            getInventories(tableId);
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

function getInventoryItemList(selectId) {
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

function getStocks(tableId) {
    var table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";
    document.getElementById('stockAddStatus').innerHTML = "";

    let inventoryID = document.querySelector('input[name="selected_inventory"]:checked');

    if(inventoryID != null) {
        let owner = inventoryID.parentNode.parentNode.cells[1].innerText;
        if(activeUsername == owner) {
            toggleAddStock(false);
        } else {
            toggleAddStock(true);
        }

        inventoryID = inventoryID.value;
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/stocks/retrieve/inventory?inventoryID=${inventoryID}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                let response = JSON.parse(this.responseText);        

                Object.entries(response).forEach((entry) => {
                    let row = table.insertRow(table.rows.length);

                    var cell1 = row.insertCell(0);
                    cell1.style.width = '20%';
                    cell1.style.height = '20px';
                    cell1.innerHTML = entry[1].stockID;
    
                    //insert td for first column
                    var cell2 = row.insertCell(1);
                    cell2.style.width = '20%';
                    cell2.style.height = '20px';
                    cell2.innerHTML = entry[1].name;
                    
                    //insert td for 2nd column and so on
                    var cell3 = row.insertCell(2);
                    cell3.style.width = '20%';
                    cell3.style.height = '20px';
                    cell3.innerHTML = entry[1].quantity;

                    //insert td for 2nd column and so on
                    var cell4 = row.insertCell(3);
                    cell4.style.width = '20%';
                    cell4.style.height = '20px';
                    cell4.innerHTML = entry[1].expirationDate;
    
                    //insert td for the checkbox
                    var checkBox = '<input class="deleteCheckbox" type="checkbox">';//create a checkbox
                    var cell0 = row.insertCell(4);
                    cell0.style.width = '5%';
                    cell0.style.height = '20px';
                    cell0.innerHTML = checkBox;//put it inside the cell
                });
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } else {
        toggleAddStock(true);
    }
}

function addStock(tableId) {
    try {
        let table = document.getElementById(tableId);

        let inventoryID = document.querySelector('input[name="selected_inventory"]:checked');
        
        if(inventoryID != null) {
            inventoryID = inventoryID.value;
            let itemID = document.getElementById('invItemSelect').value;
            let quantity = document.getElementById('quantity').value;
            let expirationDate = document.getElementById('expDate').value;

            expirationDate = parseDate(expirationDate);
            document.getElementById('stockAddStatus').innerHTML = "";
        
            let xmlhttp = new XMLHttpRequest();
            let url = `http://127.0.0.1:8080/stocks/create?accountID=${accountID}&inventoryID=${inventoryID}&itemID=${itemID}&quantity=${quantity}&expirationDate=${expirationDate}`;
            xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    getStocks(tableId);
                }
            };
            xmlhttp.open("POST", url, true);
            xmlhttp.send(); 
        }
    } catch(e) {
        if(e.name == "RangeError") {
            document.getElementById('stockAddStatus').innerHTML = "Failed: Threshold must be a valid date! (YYYY-MM-DD)";
        }
    }
}

function toggleAddStock(disabled) {
    document.getElementById('invItemSelect').disabled = disabled;
    document.getElementById('quantity').disabled = disabled;
    document.getElementById('expDate').disabled = disabled;
    document.getElementById('addStockBtn').disabled = disabled;
    document.getElementById('deleteStockBtn').disabled = disabled;
}

function getInventoryShareList(selectId) {
    let select = document.getElementById(selectId);
    select.innerHTML = '<option value="" disabled selected hidden></option>';

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/inventories/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);
        
            Object.entries(response).forEach((entry) => {
                if(entry[1].username == activeUsername) {
                    let option = document.createElement("option");
                    option.value = entry[1].inventoryID;
                    option.text = entry[1].name;
                    select.add(option);
                }
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function addInventoryShare(tableId) {
    let table = document.getElementById(tableId);

    let shareeName = document.getElementById('accName').value;
    let inventoryID = document.getElementById('invShareSelect').value;

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/shares/create?accountID=${accountID}&subjectID=${inventoryID}&shareeName=${shareeName}&type=inventory`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            getShares(tableId, 'inventory');
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}