package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>  {
	List<Inventory> findAllByAccountID(Integer accountID);
	List<Inventory> deleteAllByAccountID(Integer accountID);
	List<Inventory> deleteAllByName(String name);
}
