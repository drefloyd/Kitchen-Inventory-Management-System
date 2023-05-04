package org.jamr.jki.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jamr.jki.entity.Item;
import org.jamr.jki.entity.Store;
import org.jamr.jki.repository.DesirableRepository;
import org.jamr.jki.repository.PriceRepository;
import org.jamr.jki.entity.Desirable;
import org.jamr.jki.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
public class DesirableService {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private DesirableRepository desirableRepository;
	
	@Autowired
	public PriceRepository priceRepository;
	
	public String getDesirableJson(Integer shoppingListID) {
		List<Desirable> desirables = desirableRepository.findAllByShoppingListID(shoppingListID);
		ArrayList<Properties> list = new ArrayList<Properties>();
		Properties entry;
		Item item;
		Store store;
		Price price;
		
		for(Desirable desirable: desirables) {
			item = (Item) crudService.retrieve("Item", desirable.getItemID());
			store = (Store) crudService.retrieve("Store", desirable.getStoreID());
			price = priceRepository.findByStoreIDAndItemID(store.getStoreID(), item.getItemID());
			
			entry = new Properties();
			entry.put("desirableID", desirable.getDesirableID());
			entry.put("name", item.getName());
			entry.put("store", store.getName());
			entry.put("quantity", desirable.getQuantity());
			entry.put("checked", desirable.getChecked());
			if(price != null) {
				entry.put("price", price.getPrice());
			} else {
				entry.put("price", -1);
			}
			list.add(entry);
		}
		
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
