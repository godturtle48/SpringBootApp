package com.example.demo.query.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CustomerDetails {
	@Id
	private String accountObjectID;
	private String keyCompany;
	private String accountObjectName;
	private String accountObjectAddress;
	private String accountObjectContactName;
	private int reasonTypeID;
	private String journalMemo;
	private String employeeName;
	private String description;
	private int refTypeID;
	
	
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getKeyCompany() {
		return keyCompany;
	}
	public void setKeyCompany(String keyCompany) {
		this.keyCompany = keyCompany;
	}
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
	
	public int getReasonTypeID() {
		return reasonTypeID;
	}
	public void setReasonTypeID(int reasonTypeID) {
		this.reasonTypeID = reasonTypeID;
	}
	public String getJournalMemo() {
		return journalMemo;
	}
	public void setJournalMemo(String journalMemo) {
		this.journalMemo = journalMemo;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRefTypeID() {
		return refTypeID;
	}
	public void setRefTypeID(int refTypeID) {
		this.refTypeID = refTypeID;
	}
	
	
}
