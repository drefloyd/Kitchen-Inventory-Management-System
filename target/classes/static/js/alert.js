function getAllAlerts(tableId) {
    let table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";
    document.getElementById('alertAddStatus').innerHTML="";

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/alerts/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                let row = table.insertRow(table.rows.length);

                let cell0 = row.insertCell(0);
                cell0.style.width = '20%';
                cell0.style.height = '20px';
                cell0.innerHTML = entry[1].alertID;
    
                //insert td for first column
                let cell1 = row.insertCell(1);
                cell1.style.width = '20%';
                cell1.style.height = '20px';
                cell1.innerHTML = entry[1].inventoryName;
    
                let cell2 = row.insertCell(2);
                cell2.style.width = '20%';
                cell2.style.height = '20px';
                cell2.innerHTML = entry[1].itemName;
    
                let cell3 = row.insertCell(3);
                cell3.style.width = '20%';
                cell3.style.height = '20px';
                cell3.innerHTML = entry[1].trigger;

                let cell4 = row.insertCell(4);
                cell4.style.width = '20%';
                cell4.style.height = '20px';
                cell4.innerHTML = entry[1].threshold;
    
                //insert td for the checkbox
                let checkBox = '<input class="deleteCheckbox" type="checkbox">'; //create a checkbox
                let cell5 = row.insertCell(5);
                cell5.style.width = '5%';
                cell5.style.height = '20px';
                cell5.innerHTML = checkBox; //put it inside the cell
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function getTriggeredAlerts(tableId) {
    let table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/alerts/retrieve/triggered/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((trigger) => {
                Object.entries(trigger[1]).forEach((entry) => {
                    let row = table.insertRow(table.rows.length);
    
                    let cell0 = row.insertCell(0);
                    cell0.style.width = '20%';
                    cell0.style.height = '20px';
                    cell0.innerHTML = entry[1].alertID;
        
                    let cell1 = row.insertCell(1);
                    cell1.style.width = '20%';
                    cell1.style.height = '20px';
                    cell1.innerHTML = entry[1].inventoryName;
        
                    let cell2 = row.insertCell(2);
                    cell2.style.width = '20%';
                    cell2.style.height = '20px';
                    cell2.innerHTML = entry[1].itemName;
        
                    let cell3 = row.insertCell(3);
                    cell3.style.width = '20%';
                    cell3.style.height = '20px';
                    cell3.innerHTML = entry[1].trigger;
    
                    let cell4 = row.insertCell(4);
                    cell4.style.width = '20%';
                    cell4.style.height = '20px';
                    cell4.innerHTML = entry[1].threshold;
                });
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function addAlert(tableId) {
    try {
        let table = document.getElementById(tableId);

        let inventoryName = document.getElementById('alertInventorySelect').value;
        let itemName = document.getElementById('alertItemSelect').value;
        let trigger = document.getElementById('alertTrigger').value;
        let threshold = document.getElementById('alertThreshold').value;

        if(trigger == "date") {
            threshold = parseDate(threshold);
            document.getElementById('alertAddStatus').innerHTML="";
        } else if(trigger == "quantity") {
            if(threshold == "" || isNaN(threshold) || threshold < 0) {
                throw new TypeError();
            } else {
                document.getElementById('alertAddStatus').innerHTML="";
            }
        }
        
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/alerts/create?accountID=${accountID}&inventoryID=${inventoryName}&itemID=${itemName}&triggers=${trigger}&threshold=${threshold}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                getTriggeredAlerts('dataTableTriggeredAlerts');
                getAllAlerts('dataTableAllAlerts');
            }
        };
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
    } catch (e) {
        if(e.name == "RangeError") {
            document.getElementById('alertAddStatus').innerHTML="Failed: Trigger is date, so threshold must be a valid date! (YYYY-MM-DD)";
        } else if (e.name == "TypeError") {
            document.getElementById('alertAddStatus').innerHTML="Failed: Trigger is quantity, so threshold must be a number > 0!";
        }
    }
}

function getAlertInventoryList(selectId) {
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

function getAlertItemList(selectId) {
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