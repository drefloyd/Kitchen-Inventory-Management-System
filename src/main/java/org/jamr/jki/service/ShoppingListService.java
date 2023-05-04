package org.jamr.jki.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jamr.jki.entity.Account;
import org.jamr.jki.entity.ShoppingList;
import org.jamr.jki.entity.Share;
import org.jamr.jki.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
public class ShoppingListService {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	public ShareRepository shareRepository;
	
	public String getShoppingListJson(Integer accountID) {
		ArrayList<Properties> list = new ArrayList<Properties>();
		Properties entry;
		Account account;
		ShoppingList shoppingList;
		
		List<ShoppingList> shoppingLists = crudService.retrieveAllByAccountID("ShoppingList", accountID);
		account = (Account) crudService.retrieve("Account", accountID);
		for(ShoppingList sList: shoppingLists) {
			entry = new Properties();
			entry.put("shoppingListID", sList.getShoppingListID());
			entry.put("username", account.getUsername());
			entry.put("name", sList.getName());
			list.add(entry);
		}
		
		List<Share> shares = shareRepository.findAllByShareeIDAndType(accountID, "shoppingList");
		for(Share share: shares) {
			account = (Account) crudService.retrieve("Account", share.getAccountID());
			shoppingList = (ShoppingList) crudService.retrieve("ShoppingList", share.getSubjectID());
			
			entry = new Properties();
			entry.put("shoppingListID", shoppingList.getShoppingListID());
			entry.put("username", account.getUsername());
			entry.put("name", shoppingList.getName());
			list.add(entry);
		}

		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
