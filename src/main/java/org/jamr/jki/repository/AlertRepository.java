package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> { 
	List<Alert> findAllByAccountID(Integer accountID);
	List<Alert> deleteAllByAccountID(Integer accountID);
	List<Alert> deleteAllByInventoryID(Integer inventoryID);
	List<Alert> deleteAllByItemID(Integer itemID);
	List<Alert> deleteAllByTriggers(String triggers);
	List<Alert> deleteAllByThreshold(String threshold);
}
