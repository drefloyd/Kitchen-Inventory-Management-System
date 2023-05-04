package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="prices")
public class Price {
	@Id
	@Column(name="priceID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer priceID;

	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="itemID", nullable=false)
	private Integer itemID;
	
	@Column(name="storeID", nullable=false)
	private Integer storeID;
	
	@Column(name="price", nullable=false)
	private Double price;
	
	public Price() {
		this.accountID = 0;
		this.itemID = 0;
		this.storeID = 0;
		this.price = 0d;
	}
	
	public Price(Integer accountID,
			Integer itemID,
			Integer storeID,
			Double price) {
		this.accountID = accountID;
		this.itemID = itemID;
		this.storeID = storeID;
		this.price = price;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Integer getStoreID() {
		return storeID;
	}

	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPriceID() {
		return priceID;
	}
}
