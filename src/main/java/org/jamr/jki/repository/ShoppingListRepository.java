package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {
	List<ShoppingList> findAllByAccountID(Integer accountID);
	List<ShoppingList> deleteAllByAccountID(Integer accountID);
	List<ShoppingList> deleteAllByName(String name);
}
