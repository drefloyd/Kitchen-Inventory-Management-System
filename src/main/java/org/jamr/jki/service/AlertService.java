package org.jamr.jki.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jamr.jki.entity.Alert;
import org.jamr.jki.entity.Inventory;
import org.jamr.jki.entity.Item;
import org.jamr.jki.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
public class AlertService {
	@Autowired
	private CrudService crudService;
	
	public String getTriggeredDateAlertsJson(Integer accountID) {
		List<Alert> existingAlerts = crudService.retrieveAllByAccountID("Alert", accountID);
		ArrayList<Properties> triggeredAlerts = new ArrayList<Properties>();
		Properties entry;
		Item item;
		Inventory inventory;
		String threshold;
		boolean triggered;
		
		for(Alert alert: existingAlerts) {
			if(alert.getTrigger().equals("date")) {
				threshold = alert.getThreshold();
				triggered = isDateTriggered(threshold);
				if(triggered == true) {
					item = (Item) crudService.retrieve("Item", alert.getItemID());
					inventory = (Inventory) crudService.retrieve("Inventory", alert.getInventoryID());
					
					entry = new Properties();
					entry.put("alertID", alert.getAlertID());
					entry.put("inventoryName", inventory.getName());
					entry.put("itemName", item.getName());
					entry.put("trigger", alert.getTrigger());
					entry.put("threshold", alert.getThreshold());
					triggeredAlerts.add(entry);
				}
			}
		}
		
		Gson gson = new Gson();
		return gson.toJson(triggeredAlerts);
	}
	
	public String getTriggeredQuantityAlertsJson(Integer accountID) {
		List<Alert> existingAlerts = crudService.retrieveAllByAccountID("Alert", accountID);
		ArrayList<Properties> triggeredAlerts = new ArrayList<Properties>();
		Properties entry;
		Item item;
		Inventory inventory;
		Integer threshold;
		boolean triggered;
		
		for(Alert alert: existingAlerts) {
			if(alert.getTrigger().equals("quantity")) {
				threshold = Integer.parseInt(alert.getThreshold());
				triggered = isQuantityTriggered(threshold, accountID, alert.getItemID());
				if(triggered == true) {
					item = (Item) crudService.retrieve("Item", alert.getItemID());
					inventory = (Inventory) crudService.retrieve("Inventory", alert.getInventoryID());
					
					entry = new Properties();
					entry.put("alertID", alert.getAlertID());
					entry.put("inventoryName", inventory.getName());
					entry.put("itemName", item.getName());
					entry.put("trigger", alert.getTrigger());
					entry.put("threshold", alert.getThreshold());
					triggeredAlerts.add(entry);
				}
			}
		}
		
		Gson gson = new Gson();
		return gson.toJson(triggeredAlerts);
	}
	
	public String getAllAlertsJson(Integer accountID) {
		ArrayList<Properties> list = new ArrayList<Properties>();
		Properties entry;
		Item item;
		Inventory inventory;
		
		List<Alert> alerts = crudService.retrieveAllByAccountID("Alert", accountID);
		for(Alert alert: alerts) {
			item = (Item) crudService.retrieve("Item", alert.getItemID());
			inventory = (Inventory) crudService.retrieve("Inventory", alert.getInventoryID());
			
			entry = new Properties();
			entry.put("alertID", alert.getAlertID());
			entry.put("inventoryName", inventory.getName());
			entry.put("itemName", item.getName());
			entry.put("trigger", alert.getTrigger());
			entry.put("threshold", alert.getThreshold());
			list.add(entry);
		}

		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	public boolean isDateTriggered(String threshold) {
		LocalDate thresholdDate = LocalDate.parse(threshold);
		LocalDate now = LocalDate.now();
		return !now.isBefore(thresholdDate);
	}
	
	public boolean isQuantityTriggered(Integer threshold, Integer accountID, Integer itemID) {
		List<Stock> existingStocks = crudService.retrieveAllByAccountID("Stock", accountID);
		
		int quantity = 0;
		boolean stockExists = false;
		for(Stock stock: existingStocks) {
			if(stock.getItemID().equals(itemID)) {
				stockExists = true;
				quantity += stock.getQuantity();
			}
		}
		
		return stockExists && quantity <= threshold.intValue();
	}
}
