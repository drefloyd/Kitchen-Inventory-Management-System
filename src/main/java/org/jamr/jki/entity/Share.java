package org.jamr.jki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="shares")
public class Share {
	@Id
	@Column(name="shareID", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shareID;

	@Column(name="accountID", nullable=false)
	private Integer accountID;
	
	@Column(name="subjectID", nullable=false)
	private Integer subjectID;
	
	@Column(name="shareeID", nullable=false)
	private Integer shareeID;
	
	@Column(name="type", length=255, nullable=false)
	private String type;
	
	public Share() {
		this.accountID = 0;
		this.subjectID = 0;
		this.shareeID = 0;
		this.type = "";
	}
	
	public Share(Integer accountID, 
			Integer subjectID, 
			Integer shareeID, 
			String type) {
		this.accountID = accountID;
		this.subjectID = subjectID;
		this.shareeID = shareeID;
		this.type = type;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public Integer getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Integer subjectID) {
		this.subjectID = subjectID;
	}

	public Integer getShareeID() {
		return shareeID;
	}

	public void setShareeID(Integer shareeID) {
		this.shareeID = shareeID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getShareID() {
		return shareID;
	}
}
