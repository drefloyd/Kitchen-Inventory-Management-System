package org.jamr.jki.repository;

import org.jamr.jki.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> { 
	Account findByUsername(String username);
	Account findByUsernameAndPassword(String username, String password);
}
