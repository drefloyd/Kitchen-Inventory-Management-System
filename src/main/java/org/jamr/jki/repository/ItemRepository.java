package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findAllByAccountID(Integer accountID);
	List<Item> deleteAllByAccountID(Integer accountID);
	List<Item> deleteAllByName(String name);
	List<Item> deleteAllByCategory(String category);
	List<Item> deleteAllByNote(String note);
}
