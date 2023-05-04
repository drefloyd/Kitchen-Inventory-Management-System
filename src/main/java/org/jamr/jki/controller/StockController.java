package org.jamr.jki.controller;

import java.util.List;
import org.jamr.jki.entity.Stock;
import org.jamr.jki.service.CrudService;
import org.jamr.jki.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping(value="stocks")
public class StockController {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private StockService stockService;
	
	private static final String ENTITY_ID = "Stock";
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createStock(@NotNull @RequestParam Integer accountID,
			@NotNull @RequestParam Integer inventoryID,
			@NotNull @RequestParam Integer itemID, 
			@NotNull @RequestParam Integer quantity,
			@RequestParam(defaultValue="9999-12-30") String expirationDate) {
		Stock newStock = new Stock(accountID, inventoryID, itemID, quantity, expirationDate);
		crudService.create(ENTITY_ID, newStock);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deleteStock(@NotNull @RequestParam Integer stockID) {
		crudService.delete(ENTITY_ID, stockID);
	}
	
	@RequestMapping(value="/quantity", method=RequestMethod.PUT)
	public void changeQuantity(@NotNull @RequestParam Integer stockID,
			@NotNull @RequestParam Integer quantity) {
		Stock existingStock = (Stock) crudService.retrieve(ENTITY_ID, stockID);
		existingStock.setQuantity(quantity);
		crudService.update(ENTITY_ID, existingStock);
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllStocksByAccountID(@NotNull @RequestParam Integer accountID) {
		List<Stock> existingStocks = crudService.retrieveAllByAccountID(ENTITY_ID, accountID);
		return crudService.toJson(existingStocks);
	}
	
	@RequestMapping(value="/retrieve/inventory", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllStocksByInventoryID(@NotNull @RequestParam Integer inventoryID) {
		return stockService.getStockJson(inventoryID);
	}
}
