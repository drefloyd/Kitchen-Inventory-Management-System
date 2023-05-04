function getItems(tableId) {
    let table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/items/retrieve/all?accountID=${accountID}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                let row = table.insertRow(table.rows.length);

                let cell0 = row.insertCell(0);
                cell0.style.width = '20%';
                cell0.style.height = '20px';
                cell0.innerHTML = entry[1].itemID;
    
                //insert td for first column
                let cell1 = row.insertCell(1);
                cell1.style.width = '20%';
                cell1.style.height = '20px';
                cell1.innerHTML = entry[1].name;
    
                let cell2 = row.insertCell(2);
                cell2.style.width = '20%';
                cell2.style.height = '20px';
                cell2.innerHTML = entry[1].category;
    
                let cell3 = row.insertCell(3);
                cell3.style.width = '20%';
                cell3.style.height = '20px';
                cell3.innerHTML = entry[1].note;
    
                //insert td for the checkbox
                let checkBox = '<input class="deleteCheckbox" type="checkbox">'; //create a checkbox
                let cell4 = row.insertCell(4);
                cell4.style.width = '5%';
                cell4.style.height = '20px';
                cell4.innerHTML = checkBox; //put it inside the cell
            });
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function addItem(tableId) {
    let table = document.getElementById(tableId);

    let name = document.getElementById('iname').value;
    let category = document.getElementById('category').value;
    let note = document.getElementById('notes').value;

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/items/create?accountID=${accountID}&name=${name}&category=${category}&note=${note}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            getItems(tableId);
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}