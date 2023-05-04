package org.jamr.jki.controller;

import java.util.ArrayList;
import java.util.List;
import org.jamr.jki.entity.Desirable;
import org.jamr.jki.service.DesirableService;
import org.jamr.jki.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping(value="desirables")
public class DesirableController {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private DesirableService desirableService;
	
	private static final String ENTITY_ID = "Desirable";
	
	@RequestMapping(value="/check", method=RequestMethod.PUT, produces="application/json")
	public String check(@NotNull @RequestParam Integer desirableID) {
		Desirable existingDesirable = (Desirable) crudService.retrieve(ENTITY_ID, desirableID);
		existingDesirable.setChecked(true);
		crudService.update(ENTITY_ID, existingDesirable);
		return "{ \"checked\": \"true\" }";
	}
	
	@RequestMapping(value="/uncheck", method=RequestMethod.PUT, produces="application/json")
	public String uncheck(@NotNull @RequestParam Integer desirableID) {
		Desirable existingDesirable = (Desirable) crudService.retrieve(ENTITY_ID, desirableID);
		existingDesirable.setChecked(false);
		crudService.update(ENTITY_ID, existingDesirable);
		return "{ \"checked\": false }";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createDesirable(@NotNull @RequestParam Integer accountID,
			@NotNull @RequestParam Integer shoppingListID,
			@NotNull @RequestParam Integer storeID, 
			@NotNull @RequestParam Integer itemID,
			@NotNull @RequestParam Integer quantity) {
		Desirable desirable = new Desirable(accountID, shoppingListID, storeID, itemID, quantity);
		crudService.create(ENTITY_ID, desirable);
	}
	
	@Transactional
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteDesirable(@NotNull @RequestParam Integer desirableID) {
		Desirable desirable = (Desirable) crudService.retrieve(ENTITY_ID, desirableID);
		crudService.delete(ENTITY_ID, desirableID);
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllDesirablesByAccountID(@NotNull @RequestParam Integer accountID) {
		List<Desirable> existingDesirables = crudService.retrieveAllByAccountID(ENTITY_ID, accountID);
		return crudService.toJson(existingDesirables);
	}
	
	@RequestMapping(value="/retrieve/shopping", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllDesirablesByShoppingListID(@NotNull @RequestParam Integer shoppingListID) {
		return desirableService.getDesirableJson(shoppingListID);
	}
	
	@RequestMapping(value="/retrieve/checked", method=RequestMethod.GET, produces="application/json")
	public String retrieveCheckedDesirables(@NotNull @RequestParam Integer accountID) {
		List<Desirable> existingDesirables = crudService.retrieveAllByAccountID(ENTITY_ID, accountID);
		List<Desirable> checkedDesirables = new ArrayList<Desirable>();
		
		for(Desirable desirable: existingDesirables) {
			if(desirable.getChecked() == true) {
				checkedDesirables.add(desirable);
			}
		}
		
 		return crudService.toJson(checkedDesirables);
	}
}
