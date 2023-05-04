package org.jamr.jki.controller;

import org.jamr.jki.entity.ShoppingList;
import org.jamr.jki.repository.ShareRepository;
import org.jamr.jki.service.CrudService;
import org.jamr.jki.service.ShoppingListService;
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
@RequestMapping(value="shopping")
public class ShoppingListController {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private ShareRepository shareRepository;
	
	@Autowired
	private ShoppingListService shoppingListService;
	
	private static final String ENTITY_ID = "ShoppingList";
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createShoppingList(@NotNull @RequestParam Integer accountID,
			@NotBlank @RequestParam String name) {
		ShoppingList newShoppingList = new ShoppingList(accountID, name);
		crudService.create(ENTITY_ID, newShoppingList);
	}
	
	@Transactional
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteShoppingList(@NotNull @RequestParam Integer shoppingListID) {
		shareRepository.deleteAllBySubjectIDAndType(shoppingListID, "shoppingList");
		crudService.delete(ENTITY_ID, shoppingListID);
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllShoppingLists(@NotNull @RequestParam Integer accountID) {
		return shoppingListService.getShoppingListJson(accountID);
	}
}
