package com.example.demo.command.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//Model to put data to add payment receipt combobox
@Entity
public class GeneralDetailCommand {
	@Id
	private String accountObjectID;
	private String accountObjectName;
	private String accountObjectAddress;
	private String accountObjectContactName;
	public String getAccountObjectID() {
		return accountObjectID;
	}
	public void setAccountObjectID(String accountObjectID) {
		this.accountObjectID = accountObjectID;
	}
	public String getAccountObjectName() {
		return accountObjectName;
	}
	public void setAccountObjectName(String accountObjectName) {
		this.accountObjectName = accountObjectName;
	}
	public String getAccountObjectAddress() {
		return accountObjectAddress;
	}
	public void setAccountObjectAddress(String accountObjectAddress) {
		this.accountObjectAddress = accountObjectAddress;
	}
	public String getAccountObjectContactName() {
		return accountObjectContactName;
	}
	public void setAccountObjectContactName(String accountObjectContactName) {
		this.accountObjectContactName = accountObjectContactName;
	}
	
	
}
