package org.jamr.jki.controller;

import org.jamr.jki.entity.Account;
import org.jamr.jki.entity.Share;
import org.jamr.jki.repository.AccountRepository;
import org.jamr.jki.service.CrudService;
import org.jamr.jki.service.ShareService;
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
@RequestMapping(value="shares")
public class ShareController {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ShareService shareService;
	
	private static final String ENTITY_ID = "Share";
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createItem(@NotNull @RequestParam Integer accountID,
			@NotNull @RequestParam Integer subjectID,
			@NotNull @RequestParam String shareeName, 
			@NotBlank @RequestParam String type) {
		Account sharee = accountRepository.findByUsername(shareeName);
		Integer shareeID = sharee.getAccountID();
		Share newShare = new Share(accountID, subjectID, shareeID, type);
		crudService.create(ENTITY_ID, newShare);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteItem(@NotNull @RequestParam Integer shareID) {
		crudService.delete(ENTITY_ID, shareID);
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllShares(@NotNull @RequestParam Integer accountID,
			@NotNull @RequestParam String type) {
		String response = "{}";
		
		switch(type) {
		case "inventory":
			response = shareService.getInventorySharesJson(accountID);
			break;
		case "shoppingList":
			response = shareService.getShoppingListSharesJson(accountID);
			break;
		}

		return response;
	}
}
