package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="stores")
public class Store {
	@Id
	@Column(name="storeID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer storeID;
	
	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="name", length=255, nullable=false)
	private String name;
	
	@Column(name="location", length=255)
	private String location;

	public Store() {
		this.accountID = 0;
		this.name = "";
		this.location = "";
	}
	
	public Store(Integer accountID, String name) {
		this.accountID = accountID;
		this.name = name;
		this.location = "";
	}
	
	public Store(Integer accountID, String name, String location) {
		this.accountID = accountID;
		this.name = name;
		this.location = location;
	}
	
	public Integer getStoreID() {
		return storeID;
	}

	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAccountID() {
		return accountID;
	}
}