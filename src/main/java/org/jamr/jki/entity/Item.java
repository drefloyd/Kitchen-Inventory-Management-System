package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Item {
	@Id
	@Column(name="itemID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemID;

	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="name", length=255, nullable=false)
	private String name;
	
	@Column(name="category", length=255)
	private String category;
	
	@Column(name="note", length=255)
	private String note;
	
	public Item() {
		this.accountID = 0;
		this.name = "";
		this.category = "";
		this.note = "";
	}
	
	public Item(Integer accountID, String name) {
		this.accountID = accountID;
		this.name = name;
		this.category = "";
		this.note = "";
	}
	
	public Item(Integer accountID, String name, String category, String note) {
		this.accountID = accountID;
		this.name = name;
		this.category = category;
		this.note = note;
	}

	public Integer getItemID() {
		return itemID;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
