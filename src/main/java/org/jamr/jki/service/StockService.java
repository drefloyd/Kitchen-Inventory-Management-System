package org.jamr.jki.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jamr.jki.entity.Item;
import org.jamr.jki.entity.Stock;
import org.jamr.jki.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
public class StockService {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private StockRepository stockRepository;
	
	public String getStockJson(Integer inventoryID) {
		List<Stock> stocks = stockRepository.findAllByInventoryID(inventoryID);
		ArrayList<Properties> list = new ArrayList<Properties>();
		Properties entry;
		Item item;
		
		for(Stock stock: stocks) {
			item = (Item) crudService.retrieve("Item", stock.getItemID());
			
			entry = new Properties();
			entry.put("stockID", stock.getStockID());
			entry.put("name", item.getName());
			entry.put("quantity", stock.getQuantity());
			entry.put("expirationDate", stock.getExpirationDate());
			list.add(entry);
		}
		
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
