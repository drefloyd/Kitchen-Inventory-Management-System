package org.jamr.jki.service;

import java.util.List;
import org.jamr.jki.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
public class CrudService {
	@Autowired
	private AccountRepository accountRepository;
	 
	@Autowired
	public AlertRepository alertRepository;
	
	@Autowired
	public DesirableRepository desirableRepository;
	
	@Autowired
	public InventoryRepository inventoryRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	public PriceRepository priceRepository;
	
	@Autowired
	public ShareRepository shareRepository;

	@Autowired
	public ShoppingListRepository shoppingListRepository;
	
	@Autowired
	public StockRepository stockRepository;
	
	@Autowired
	private StoreRepository storeRepository;

	public String toJson(Object entity) {
		Gson gson = new Gson();
		return gson.toJson(entity);
	}
	
	public void create(String repositoryName, Object entity) {
		JpaRepository repository = getRepository(repositoryName);
		repository.save(entity);
	}
	
	public Object retrieve(String repositoryName, Integer entityID) {
		JpaRepository repository = getRepository(repositoryName);
		return repository.getReferenceById(entityID);
	}
	
	public List retrieveAllByAccountID(String repositoryName, Integer accountID) {
		List list;
		switch(repositoryName) {
		case "Alert":
			list = alertRepository.findAllByAccountID(accountID);
			break;
		case "Desirable":
			list = desirableRepository.findAllByAccountID(accountID);
			break;
		case "Inventory":
			list = inventoryRepository.findAllByAccountID(accountID);
			break;
		case "Item":
			list = itemRepository.findAllByAccountID(accountID);
			break;
		case "Price":
			list = priceRepository.findAllByAccountID(accountID);
			break;
		case "Share":
			list = shareRepository.findAllByAccountID(accountID);
			break;
		case "ShoppingList":
			list = shoppingListRepository.findAllByAccountID(accountID);
			break;
		case "Stock":
			list = stockRepository.findAllByAccountID(accountID);
			break;
		case "Store":
			list = storeRepository.findAllByAccountID(accountID);
			break;
		default:
			list = null;
		}
		return list;
	}
	
	public void update(String repositoryName, Object entity) {
		JpaRepository repository = getRepository(repositoryName);
		repository.save(entity);
	}
	
	public void delete(String repositoryName, Integer entityID) {
		JpaRepository repository = getRepository(repositoryName);
		Object entity = retrieve(repositoryName, entityID);
		repository.delete(entity);
	}
	
	private JpaRepository getRepository(String repositoryName) {
		JpaRepository repository;
		switch(repositoryName) {
		case "Account":
			repository = accountRepository;
			break;
		case "Alert":
			repository = alertRepository;
			break;
		case "Desirable":
			repository = desirableRepository;
			break;
		case "Inventory":
			repository = inventoryRepository;
			break;
		case "Item":
			repository = itemRepository;
			break;
		case "Price":
			repository = priceRepository;
			break;
		case "Share":
			repository = shareRepository;
			break;
		case "ShoppingList":
			repository = shoppingListRepository;
			break;
		case "Stock":
			repository = stockRepository;
			break;
		case "Store":
			repository = storeRepository;
			break;
		default:
			repository = null;
		}
		return repository;
	}
}
