package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	List<Stock> findAllByInventoryID(Integer inventoryID);
	List<Stock> findAllByAccountIDAndInventoryID(Integer accountID, Integer inventoryID);
	List<Stock> findAllByAccountID(Integer accountID);
	List<Stock> deleteAllByAccountID(Integer accountID);
	List<Stock> deleteAllByInventoryID(Integer inventoryID);
	List<Stock> deleteAllByItemID(Integer itemID);
	List<Stock> deleteAllByQuantity(Integer quantity);
	List<Stock> deleteAllByExpirationDate(String expirationDate);
}