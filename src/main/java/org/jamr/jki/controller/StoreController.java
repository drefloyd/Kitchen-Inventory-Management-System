package org.jamr.jki.controller;

import java.util.List;
import org.jamr.jki.entity.Store;
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
@RequestMapping(value="stores")
public class StoreController {
	@Autowired
	private CrudService crudService;
	
	private static final String ENTITY_ID = "Store";
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createStore(@NotNull @RequestParam Integer accountID,
			@NotBlank @RequestParam String name,
			@RequestParam(defaultValue="") String location) {
		Store newStore = new Store(accountID, name, location);
		crudService.create(ENTITY_ID, newStore);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteStore(@NotNull @RequestParam Integer storeID) {
		crudService.delete(ENTITY_ID, storeID);
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllStores(@NotNull @RequestParam Integer accountID) {
		List<Store> existingStores = crudService.retrieveAllByAccountID(ENTITY_ID, accountID);
		return crudService.toJson(existingStores);
	}
}
