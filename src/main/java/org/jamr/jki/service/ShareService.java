package org.jamr.jki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jamr.jki.entity.Account;
import org.jamr.jki.entity.Inventory;
import org.jamr.jki.entity.ShoppingList;
import org.jamr.jki.entity.Share;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.google.gson.Gson;

@Service
public class ShareService {
	@Autowired
	private CrudService crudService;
	
	public String getInventorySharesJson(Integer accountID) {
		List<Share> shares = crudService.retrieveAllByAccountID("Share", accountID);
		ArrayList<Properties> list = new ArrayList<Properties>();
		Properties entry;
		Account account;
		Inventory inventory;
		
		for(Share share: shares) {
			if(share.getType().equals("inventory")) {
				account = (Account) crudService.retrieve("Account", share.getShareeID());
				inventory = (Inventory) crudService.retrieve("Inventory", share.getSubjectID());
				
				entry = new Properties();
				entry.put("shareID", share.getShareID());
				entry.put("username", account.getUsername());
				entry.put("subjectID", share.getSubjectID());
				entry.put("name", inventory.getName());
				list.add(entry);
			}
		}
		
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	public String getShoppingListSharesJson(Integer accountID) {
		List<Share> shares = crudService.retrieveAllByAccountID("Share", accountID);
		ArrayList<Properties> list = new ArrayList<Properties>();
		Properties entry;
		Account account;
		ShoppingList shoppingList;
		
		for(Share share: shares) {
			if(share.getType().equals("shoppingList")) {
				account = (Account) crudService.retrieve("Account", share.getShareeID());
				shoppingList = (ShoppingList) crudService.retrieve("ShoppingList", share.getSubjectID());
				
				entry = new Properties();
				entry.put("shareID", share.getShareID());
				entry.put("username", account.getUsername());
				entry.put("subjectID", share.getSubjectID());
				entry.put("name", shoppingList.getName());
				list.add(entry);
			}
		}
		
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
