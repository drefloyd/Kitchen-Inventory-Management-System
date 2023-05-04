var accountID = null;
var activeUsername = null;

function deleteChecked(tableId, path, idName) {  // deletes checked row from table 
    let table = document.getElementById(tableId);
    let checkboxes = table.querySelectorAll('.deleteCheckbox:checked');

    Object.entries(checkboxes).forEach((entry) => {
        let id = entry[1].parentElement.parentElement.cells[0].innerHTML;
        let xmlhttp = new XMLHttpRequest();
        let url = `http://127.0.0.1:8080/${path}/delete?${idName}=${id}`;
        xmlhttp.open("DELETE", url, true);
        xmlhttp.send();
    });
    
    checkboxes.forEach(checkbox => checkbox.parentElement.parentElement.remove()); //removing its parent parent (row), its direct parent elemnt is the cell
}

function deleteCheckedWithRefresh(tableId, path, idName, tabId) {
    deleteChecked(tableId, path, idName);
    setTimeout(document.getElementById(tabId).click(), 1000);
}

function getShares(tableId, type) {
    let table = document.getElementById(tableId);
    table.innerHTML="<tbody></tbody>";

    let xmlhttp = new XMLHttpRequest();
    let url = `http://127.0.0.1:8080/shares/retrieve/all?accountID=${accountID}&type=${type}`;
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let response = JSON.parse(this.responseText);

            Object.entries(response).forEach((entry) => {
                let row = table.insertRow(table.rows.length);

                var cell4 = row.insertCell(0);
                cell4.style.width = '20%';
                cell4.style.height = '20px';
                cell4.innerHTML = entry[1].shareID;

                var cell1 = row.insertCell(1);
                cell1.style.width = '20%';
                cell1.style.height = '20px';
                cell1.innerHTML = entry[1].username;

                var cell2 = row.insertCell(2);
                cell2.style.width = '20%';
                cell2.style.height = '20px';
                cell2.innerHTML = entry[1].subjectID;

                var cell3 = row.insertCell(3);
                cell3.style.width = '20%';
                cell3.style.height = '20px';
                cell3.innerHTML = entry[1].name;

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
}

function parseDate(dateString) {
    if(dateString.length != 10) {
        throw new RangeError;
    } 
    let date = new Date(dateString);
    return date.toISOString().substring(0, 10);
}