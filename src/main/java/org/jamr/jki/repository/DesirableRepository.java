package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.Desirable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesirableRepository extends JpaRepository<Desirable, Integer> {
	List<Desirable> findAllByShoppingListID(Integer shoppingListID);
	List<Desirable> findAllByAccountID(Integer accountID);
	List<Desirable> deleteAllByAccountID(Integer accountID);
	List<Desirable> deleteAllByShoppingListID(Integer shoppingListID);
	List<Desirable> deleteAllByStoreID(Integer storeID);
	List<Desirable> deleteAllByItemID(Integer itemID);
	List<Desirable> deleteAllByQuantity(Integer quantity);
	List<Desirable> findAllByChecked(Integer accountID);
}
