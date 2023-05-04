package org.jamr.jki.repository;

import java.util.List;
import org.jamr.jki.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	List<Store> findAllByAccountID(Integer accountID);
	List<Store> deleteAllByAccountID(Integer accountID);
	List<Store> deleteAllByName(String name);
	List<Store> deleteAllByLocation(String location);
}
