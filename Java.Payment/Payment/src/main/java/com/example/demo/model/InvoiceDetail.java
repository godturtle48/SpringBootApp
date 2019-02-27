package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class InvoiceDetail {
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	private String refDetailID;
	@NotNull
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="refID")
	private PaymentReceipt payment;
	
	private String discription;
	@NotNull
	private Double amountOC;
	@NotNull
	private Double amount;
	private String accountObjectID;
	private int sortOrder;
	
	//trang thai 0:unmodify
	//1:modify
	//2:delete
	//3;add
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	//=====================================
	public String getRefDetailID() {
		return refDetailID;
	}
	public void setRefDetailID(String refDetailID) {
		this.refDetailID = refDetailID;
	}
	@JsonIgnore
	public PaymentReceipt getPayment() {
		return payment;
	}
	public void setPayment(PaymentReceipt payment) {
		this.payment = payment;
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


}
