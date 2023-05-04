package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {
	@Id
	@Column(name="accountID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accountID;
	
	@Column(name="username", length=255, nullable=false, unique=true)
	private String username;
	
	@Column(name="password", length=255, nullable=false)
	private String password;
	
	@Column(name="active", length=1, nullable=false)
	private Boolean active;
	
	public Account() { 
		setUsername(""); 
		setPassword("");
		setActive(false);
	}
	
	public Account(String username, String password) { 
		this(username, password, false);
	}
	
	public Account(String username, String password, Boolean active) { 
		setUsername(username); 
		setPassword(password);
		setActive(active);
	}
	
	public Integer getAccountID() {
		return this.accountID;
	}
	
	public String getUsername() { 
		return this.username; 
	}
	
	public String getPassword() { 
		return this.password; 
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public void setUsername(String username) { 
		this.username = username; 
	}
	
	public void setPassword(String password) { 
		this.password = password; 
	}
	
	public void setActive(boolean active) { 
		this.active = active; 
	}
}
