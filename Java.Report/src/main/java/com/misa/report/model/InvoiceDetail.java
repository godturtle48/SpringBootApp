package com.misa.report.model;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;



public class InvoiceDetail {
	private String refDetailID;
	private String discription;
	private Double amountOC;
	private Double amount;
	private String accountObjectID;
	private int sortOrder;
	private int status;
	public String getRefDetailID() {
		return refDetailID;
	}
	public void setRefDetailID(String refDetailID) {
		this.refDetailID = refDetailID;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Double getAmountOC() {
		return amountOC;
	}
	public void setAmountOC(Double amountOC) {
		this.amountOC = amountOC;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAccountObjectID() {
		return accountObjectID;
	}
	public void setAccountObjectID(String accountObjectID) {
		this.accountObjectID = accountObjectID;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String toString() {
		JSONObject obj=new JSONObject();
		obj.put("refDetailID",refDetailID);
		obj.put("discription", discription);
		obj.put("amountOC", amountOC);
		obj.put("amount", amount);
		obj.put("accountObjectID", accountObjectID);
		obj.put("sortOrder", sortOrder);
		obj.put("status", status);
		return obj.toJSONString();
	}
	
}
