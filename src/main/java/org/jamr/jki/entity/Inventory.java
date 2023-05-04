package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="inventories")
public class Inventory {
	@Id
	@Column(name="inventoryID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer inventoryID;

	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="name", length=255, nullable=false)
	private String name;
	
	public Inventory() {
		this.accountID = 0;
		this.name = "";
	}
	
	public Inventory(Integer accountID, String name) {
		this.accountID = accountID;
		this.name = name;
	}

	public Integer getInventoryID() {
		return inventoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAccountID() {
		return accountID;
	}
	
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
}
