function tabAccess(event, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    event.currentTarget.className += " active";

    switch(tabName) {
        case "Item":
            getItems('dataTable1');
            break;
        case "Store":
            getStores('dataTableStores');
            getStoreItemList('itemFromList');
            getPrices('dataTableItems');
            break;
        case "Inventory":
            getInventories('dataTableInventories');
            getInventoryItemList('invItemSelect');
            getStocks('dataInvItems');
            getShares('dataInvShares', "inventory");
            getInventoryShareList('invShareSelect');
            break;
        case "ShoppingList":
            getShoppingLists('dataTableShoppingList');
            getShoppingListItemList('desirableSelect');
            getShoppingListStoreList('desirableStoreSelect');
            getDesirables('dataTableDesirable');
            getShares('dataTableShoppingListShares', 'shoppingList');
            getShoppingListShareList('shoppingListShareSelect');
            break;
        case "Alert":
            getTriggeredAlerts('dataTableTriggeredAlerts');
            getAllAlerts('dataTableAllAlerts');
            getAlertInventoryList('alertInventorySelect');
            getAlertItemList('alertItemSelect');
            break;
    }
}

function toggleTabs(disabled) {
    document.getElementById("accSetButton").disabled = disabled;
    document.getElementById("itemsTabButton").disabled = disabled;
    document.getElementById("storesTabButton").disabled = disabled;
    document.getElementById("invTabButton").disabled = disabled;
    document.getElementById("shoppinListButton").disabled = disabled;
    document.getElementById("alertsTabButton").disabled = disabled;
}

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById("defaultOpen").click();
    toggleTabs(true);
});