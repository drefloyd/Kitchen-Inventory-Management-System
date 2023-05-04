package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="stocks")
public class Stock {
	@Id
	@Column(name="stockID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer stockID;

	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="inventoryID", nullable=false)
	private Integer inventoryID;
	
	@Column(name="itemID", nullable=false)
	private Integer itemID;
	
	@Column(name="quantity", nullable=false)
	private Integer quantity;
	
	@Column(name="expirationDate", columnDefinition="DATE")
	private String expirationDate;

	public Stock() {
		this.accountID = 0;
		this.inventoryID = 0;
		this.itemID = 0;
		this.quantity = 0;
		this.expirationDate = "";
	}
	
	public Stock(Integer accountID, 
			Integer inventoryID, 
			Integer itemID, 
			Integer quantity) {
		this(accountID, inventoryID, itemID, quantity, "");
	}
	
	public Stock(Integer accountID, 
			Integer inventoryID, 
			Integer itemID, 
			Integer quantity, 
			String expirationDate) {
		this.accountID = accountID;
		this.inventoryID = inventoryID;
		this.itemID = itemID;
		this.quantity = quantity;
		this.expirationDate = expirationDate;
	}
	
	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public Integer getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(Integer inventoryID) {
		this.inventoryID = inventoryID;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getStockID() {
		return stockID;
	}
}
