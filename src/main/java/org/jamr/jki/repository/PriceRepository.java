package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {
	List<Price> findAllByAccountID(Integer accountID);
	List<Price> findAllByAccountIDAndStoreID(Integer accountID, Integer storeID);
	Price findByStoreIDAndItemID(Integer storeID, Integer itemID);
	List<Price> deleteAllByAccountID(Integer accountID);
	List<Price> deleteAllByStoreIDAndItemID(Integer storeID, Integer itemID);
	List<Price> deleteAllByItemID(Integer itemID);
	List<Price> deleteAllByStoreID(Integer storeID);
	List<Price> deleteAllByPrice(Double price);
}
