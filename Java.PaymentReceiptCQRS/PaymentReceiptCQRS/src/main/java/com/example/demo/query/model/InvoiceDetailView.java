package com.example.demo.query.model;




public class InvoiceDetailView {


	private String invoiceId;
	private String discription;
	private Double amountOC;
	private Double amount;
	private String accountObjectID;
	private int sortOrder;
	
	//trang thai 0:unmodify
	//1:modify
	//2:delete
	//3;add
	private int status;

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
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

	public InvoiceDetailView(String invoiceId, String discription, Double amountOC, Double amount,
			String accountObjectID, int sortOrder, int status) {
		super();
		this.invoiceId = invoiceId;
		this.discription = discription;
		this.amountOC = amountOC;
		this.amount = amount;
		this.accountObjectID = accountObjectID;
		this.sortOrder = sortOrder;
		this.status = status;
	}

	public InvoiceDetailView() {
		super();
	}
	
}
