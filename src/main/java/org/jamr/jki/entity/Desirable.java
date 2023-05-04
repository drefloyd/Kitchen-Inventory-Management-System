package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="desirables")
public class Desirable {
	@Id
	@Column(name="desirableID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer desirableID;

	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="shoppingListID", nullable=false)
	private Integer shoppingListID;
	
	@Column(name="storeID", nullable=false)
	private Integer storeID;
	
	@Column(name="itemID", nullable=false)
	private Integer itemID;
	
	@Column(name="quantity", nullable=false)
	private Integer quantity;
	
	@Column(name="checked", nullable=false)
	private Boolean checked;
	
	public Desirable() {
		this(0, 0, 0, 0, 0, false);
	}
	
	public Desirable(Integer accountID,
			Integer shoppingListID,
			Integer storeID,
			Integer itemID,
			Integer quantity) {
		this(accountID, shoppingListID, storeID, itemID, quantity, false);
	}
	
	public Desirable(Integer accountID,
			Integer shoppingListID,
			Integer storeID,
			Integer itemID,
			Integer quantity,
			Boolean checked) {
		this.accountID = accountID;
		this.shoppingListID = shoppingListID;
		this.storeID = storeID;
		this.itemID = itemID;
		this.quantity = quantity;
		this.checked = checked;
	}
	
	public Integer getDesirableID() {
		return desirableID;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public Integer getShoppingListID() {
		return shoppingListID;
	}

	public void setShoppingListID(Integer shoppingListID) {
		this.shoppingListID = shoppingListID;
	}

	public Integer getStoreID() {
		return storeID;
	}

	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
