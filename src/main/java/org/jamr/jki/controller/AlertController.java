package org.jamr.jki.controller;

import org.jamr.jki.entity.Alert;
import org.jamr.jki.service.AlertService;
import org.jamr.jki.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping(value="alerts")
public class AlertController {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private AlertService alertService;
	
	private static final String ENTITY_ID = "Alert";
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createAlert(@NotNull @RequestParam Integer accountID,
			@NotNull @RequestParam Integer inventoryID,
			@NotNull @RequestParam Integer itemID, 
			@NotBlank @RequestParam String triggers,
			@NotBlank @RequestParam String threshold) {
		Alert newAlert = new Alert(accountID, inventoryID, itemID, triggers, threshold);
		crudService.create(ENTITY_ID, newAlert);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteAlert(@NotNull @RequestParam Integer alertID) {
		crudService.delete(ENTITY_ID, alertID);
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllAlerts(@NotNull @RequestParam Integer accountID) {
		return alertService.getAllAlertsJson(accountID);
	}
	
	@RequestMapping(value="/retrieve/triggered/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllTriggeredAlerts(@NotNull @RequestParam Integer accountID) {
		String dateAlerts = retrieveTriggeredDateAlerts(accountID);
		String quantityAlerts = retrieveTriggeredQuantityAlerts(accountID);
		return String.format("{ \"date\": %s, \"quantity\": %s }", dateAlerts, quantityAlerts);
	}
	
	@RequestMapping(value="/retrieve/triggered/date", method=RequestMethod.GET, produces="application/json")
	public String retrieveTriggeredDateAlerts(@NotNull @RequestParam Integer accountID) {
		return alertService.getTriggeredDateAlertsJson(accountID);
	}
	
	@RequestMapping(value="/retrieve/triggered/quantity", method=RequestMethod.GET, produces="application/json")
	public String retrieveTriggeredQuantityAlerts(@NotNull @RequestParam Integer accountID) {
		return alertService.getTriggeredQuantityAlertsJson(accountID);
	}
}
