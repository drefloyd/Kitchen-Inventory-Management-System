package org.jamr.jki.controller;

import org.jamr.jki.entity.Inventory;
import org.jamr.jki.repository.ShareRepository;
import org.jamr.jki.service.InventoryService;
import org.jamr.jki.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping(value="inventories")
public class InventoryController {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private ShareRepository shareRepository;
	
	@Autowired
	private InventoryService inventoryService;
	
	private static final String ENTITY_ID = "Inventory";
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createInventory(@NotNull @RequestParam Integer accountID,
			@NotBlank @RequestParam String name) {
		Inventory newInventory = new Inventory(accountID, name);
		crudService.create(ENTITY_ID, newInventory);
	}
	
	@Transactional
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteInventory(@NotNull @RequestParam Integer inventoryID) {
		shareRepository.deleteAllBySubjectIDAndType(inventoryID, "inventory");
		crudService.delete(ENTITY_ID, inventoryID);
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllInventories(@NotNull @RequestParam Integer accountID) {
		return inventoryService.getInventoriesJson(accountID);
	}
}
