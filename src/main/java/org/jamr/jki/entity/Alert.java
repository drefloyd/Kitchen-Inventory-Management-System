package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="alerts")
public class Alert {
	@Id
	@Column(name="alertID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer alertID;

	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="inventoryID", nullable=false)
	private Integer inventoryID;
	
	@Column(name="itemID", nullable=false)
	private Integer itemID;
	
	@Column(name="triggers", length=255, nullable=false)
	private String triggers;
	
	@Column(name="threshold", length=255, nullable=false)
	private String threshold;
	
	public Alert() {
		this.accountID = 0;
		this.inventoryID = 0;
		this.itemID = 0;
		this.triggers = "";
		this.threshold = "";
	}
	
	public Alert(Integer accountID, 
			Integer inventoryID, 
			Integer itemID, 
			String trigger, 
			String threshold) {
		this.accountID = accountID;
		this.inventoryID = inventoryID;
		this.itemID = itemID;
		this.triggers = trigger;
		this.threshold = threshold;
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

	public String getTrigger() {
		return triggers;
	}

	public void setTriggers(String triggers) {
		this.triggers = triggers;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public Integer getAlertID() {
		return alertID;
	}
}
