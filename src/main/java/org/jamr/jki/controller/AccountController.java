package org.jamr.jki.controller;

import org.jamr.jki.entity.Account;
import org.jamr.jki.repository.AccountRepository;
import org.jamr.jki.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

@Validated
@RestController
@RequestMapping(value="accounts")
public class AccountController {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private static final String ENTITY_ID = "Account";
	
	@RequestMapping(value="/login", method=RequestMethod.PUT, produces="application/json")
	public String login(@NotBlank @RequestParam String username,
			@NotBlank @RequestParam String password) {	
		Account existingAccount = accountRepository.findByUsernameAndPassword(username, password);
		String response = "";
		if(existingAccount != null) {
			existingAccount.setActive(true);
			crudService.update(ENTITY_ID, existingAccount);
			response = String.format("{ \"accountID\":%s, \"username\":\"%s\" }", 
					existingAccount.getAccountID().toString(),
					existingAccount.getUsername());
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.PUT)
	public void logout(@NotNull @RequestParam Integer accountID) {
		Account existingAccount = (Account) crudService.retrieve("Account", accountID);
		existingAccount.setActive(false);
		crudService.update(ENTITY_ID, existingAccount);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createAccount(@NotBlank @RequestParam String username, 
			@NotBlank @RequestParam String password) {
		Account newAccount = new Account(username, password);
		crudService.create(ENTITY_ID, newAccount);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteAccount(@NotNull @RequestParam Integer accountID) {
		crudService.delete(ENTITY_ID, accountID);
	}
	
	@RequestMapping(value="/password/update", method=RequestMethod.PUT)
	public void updatePassword(@NotNull @RequestParam Integer accountID,
			@NotBlank @RequestParam String oldPassword,
			@NotBlank @RequestParam String newPassword) {
		Account existingAccount = (Account) crudService.retrieve("Account", accountID);
		if(existingAccount.getPassword().equals(oldPassword)) {
			existingAccount.setPassword(newPassword);
			crudService.update(ENTITY_ID, existingAccount);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
