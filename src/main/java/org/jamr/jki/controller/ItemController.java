package org.jamr.jki.controller;

import java.util.List;
import org.hibernate.Hibernate;
import org.jamr.jki.entity.Item;
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
@RequestMapping(value="items")
public class ItemController {
	@Autowired
	private CrudService crudService;
	
	private static final String ENTITY_ID = "Item";
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createItem(@NotNull @RequestParam Integer accountID,
			@NotBlank @RequestParam String name,
			@RequestParam(defaultValue = "") String category, 
			@RequestParam(defaultValue = "") String note) {
		Item newItem = new Item(accountID, name, category, note);
		crudService.create(ENTITY_ID, newItem);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteItem(@NotNull @RequestParam Integer itemID) {
		crudService.delete(ENTITY_ID, itemID);
	}
	
	@RequestMapping(value="/retrieve/single", method=RequestMethod.GET, produces="application/json")
	public String retrieveSingleItem(@NotNull @RequestParam Integer itemID) {
		Item existingItem = (Item) crudService.retrieve(ENTITY_ID, itemID);
		return crudService.toJson(Hibernate.unproxy(existingItem));
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllItem(@NotNull @RequestParam Integer accountID) {
		List<Item> existingItems = crudService.retrieveAllByAccountID(ENTITY_ID, accountID);
		return crudService.toJson(existingItems);
	}
}
