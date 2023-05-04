function getStores(tableId) {
    var table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/stores/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                let row = table.insertRow(table.rows.length);

                var cell1 = row.insertCell(0);
                cell1.style.width = '20%';
                cell1.style.height = '20px';
                cell1.innerHTML = entry[1].storeID;

                //insert td for first column
                var cell2 = row.insertCell(1);
                cell2.style.width = '20%';
                cell2.style.height = '20px';
                cell2.innerHTML = entry[1].name;

                //insert td for 2nd column and so on
                var cell3 = row.insertCell(2);
                cell3.style.width = '20%';
                cell3.style.height = '20px';
                cell3.innerHTML = entry[1].location;

                //insert td for the checkbox
                var checkBox = '<input class="deleteCheckbox" type="checkbox">';//create a checkbox
                var cell0 = row.insertCell(3);
                cell0.style.width = '5%';
                cell0.style.height = '20px';
                cell0.innerHTML = checkBox;//put it inside the cell

                //insert td for the radio button
                var radioButton = `<input value="${entry[1].storeID}" type="radio" name="selected_store" onclick="getPrices('dataTableItems')">`; //create a checkbox
                var cell5 = row.insertCell(4);
                cell5.style.width = '5%';
                cell5.style.height = '20px';
                cell5.innerHTML = radioButton;//put it inside the cell
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function addStore(tableId) {
    let table = document.getElementById(tableId);

    let name = document.getElementById('sname').value;
    let location = document.getElementById('SLoc').value;

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/stores/create?accountID=${accountID}&name=${name}&location=${location}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            getStores(tableId);
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

function getStoreItemList(selectId) {
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

function getPrices(tableId) {
    var table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";
    let storeID = document.querySelector('input[name="selected_store"]:checked');

    if(storeID != null) {
        toggleAddPrice(false);

        storeID = storeID.value;
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/prices/retrieve/store?accountID=${accountID}&storeID=${storeID}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                let response = JSON.parse(this.responseText);
    
                Object.entries(response).forEach((entry) => {
                    let row = table.insertRow(table.rows.length);
    
                    let list = document.getElementById("itemFromList");
                    let name;
                    Object.entries(list).forEach((option) => {
                        if(option[1].value == entry[1].itemID) {
                            name = option[1].innerText;
                        }
                    });
    
                    var cell1 = row.insertCell(0);
                    cell1.style.width = '20%';
                    cell1.style.height = '20px';
                    cell1.innerHTML = entry[1].priceID;
    
                    //insert td for first column
                    var cell2 = row.insertCell(1);
                    cell2.style.width = '20%';
                    cell2.style.height = '20px';
                    cell2.innerHTML = name;
                    
    
                    //insert td for 2nd column and so on
                    var cell3 = row.insertCell(2);
                    cell3.style.width = '20%';
                    cell3.style.height = '20px';
                    cell3.innerHTML = `\$${entry[1].price.toFixed(2)}`;
    
                    //insert td for the checkbox
                    var checkBox = '<input class="deleteCheckbox" type="checkbox">';//create a checkbox
                    var cell0 = row.insertCell(3);
                    cell0.style.width = '5%';
                    cell0.style.height = '20px';
                    cell0.innerHTML = checkBox;//put it inside the cell
                });
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } else {
        toggleAddPrice(true);
    }
}

function addStorePrice(tableId) {
    let table = document.getElementById(tableId);
 
    let price = document.getElementById('itemPrice').value;
    if(price == "" || isNaN(price) || price < 0) {
        document.getElementById('addPriceStatus').innerText = "Failed: Price must be a number > 0!";
    } else {
        document.getElementById('addPriceStatus').innerText = "";

        let storeID = document.querySelector('input[name="selected_store"]:checked').value;
        let itemID = document.getElementById('itemFromList').value;
    
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/prices/create?accountID=${accountID}&itemID=${itemID}&storeID=${storeID}&price=${price}`;
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                getPrices(tableId);
            }
        };
        xmlhttp.open("POST", url, true);
        xmlhttp.send(); 
    }
}

function toggleAddPrice(disabled) {
    document.getElementById('itemFromList').disabled = disabled;
    document.getElementById('itemPrice').disabled = disabled;
    document.getElementById('storeAddPriceBtn').disabled = disabled;
    document.getElementById('storeDeletePriceBtn').disabled = disabled;
}