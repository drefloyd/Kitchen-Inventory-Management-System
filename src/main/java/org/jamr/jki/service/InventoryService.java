package org.jamr.jki.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jamr.jki.entity.Account;
import org.jamr.jki.entity.Inventory;
import org.jamr.jki.entity.Share;
import org.jamr.jki.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
public class InventoryService {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	public ShareRepository shareRepository;
	
	public String getInventoriesJson(Integer accountID) {
		ArrayList<Properties> list = new ArrayList<Properties>();
		Properties entry;
		Account account;
		Inventory inventory;
		
		List<Inventory> inventories = crudService.retrieveAllByAccountID("Inventory", accountID);
		account = (Account) crudService.retrieve("Account", accountID);
		for(Inventory inv: inventories) {
			entry = new Properties();
			entry.put("inventoryID", inv.getInventoryID());
			entry.put("username", account.getUsername());
			entry.put("name", inv.getName());
			list.add(entry);
		}
		
		List<Share> shares = shareRepository.findAllByShareeIDAndType(accountID, "inventory");
		for(Share share: shares) {
			account = (Account) crudService.retrieve("Account", share.getAccountID());
			inventory = (Inventory) crudService.retrieve("Inventory", share.getSubjectID());
			
			entry = new Properties();
			entry.put("inventoryID", inventory.getInventoryID());
			entry.put("username", account.getUsername());
			entry.put("name", inventory.getName());
			list.add(entry);
		}

		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
