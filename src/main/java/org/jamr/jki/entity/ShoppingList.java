package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="shopping_lists")
public class ShoppingList {
	@Id
	@Column(name="shoppingListID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shoppingListID;

	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="name", length=255, nullable=false)
	private String name;
	
	public ShoppingList() {
		this.accountID = 0;
		this.name = "";
	}
	
	public ShoppingList(Integer accountID, String name) {
		this.accountID = accountID;
		this.name = name;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getShoppingListID() {
		return shoppingListID;
	}
}
