package org.jamr.jki.controller;

import java.util.List;
import org.jamr.jki.entity.Price;
import org.jamr.jki.repository.PriceRepository;
import org.jamr.jki.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping(value="prices")
public class PriceController {
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private PriceRepository priceRepository;
	
	private static final String ENTITY_ID = "Price";
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createPrice(@NotNull @RequestParam Integer accountID,
			@NotNull @RequestParam Integer itemID,
			@NotNull @RequestParam Integer storeID, 
			@NotNull @RequestParam Double price) {
		Price newPrice = new Price(accountID, itemID, storeID, price);
		crudService.create(ENTITY_ID, newPrice);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void deletePrice(@NotNull @RequestParam Integer priceID) {
		crudService.delete(ENTITY_ID, priceID);
	}
	
	@RequestMapping(value="/retrieve/all", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllPrices(@NotNull @RequestParam Integer accountID) {
		List<Price> existingPrices = crudService.retrieveAllByAccountID(ENTITY_ID, accountID);
		return crudService.toJson(existingPrices);
	}
	
	@RequestMapping(value="/retrieve/store", method=RequestMethod.GET, produces="application/json")
	public String retrieveAllPrices(@NotNull @RequestParam Integer accountID,
			@NotNull @RequestParam Integer storeID) {
		List<Price> existingPrices = priceRepository.findAllByAccountIDAndStoreID(accountID, storeID);
		return crudService.toJson(existingPrices);
	}
}
