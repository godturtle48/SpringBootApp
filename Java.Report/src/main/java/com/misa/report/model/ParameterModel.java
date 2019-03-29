package com.misa.report.model;

import java.util.Date;
import java.util.List;

public class ParameterModel {
	private String branch;
	private Date fromDate;
	private Date toDate;
	private String currencyID;
	private List<Integer> amount;
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getCurrencyID() {
		return currencyID;
	}
	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}
	public List<Integer> getAmount() {
		return amount;
	}
	public void setAmount(List<Integer> amount) {
		this.amount = amount;
	}
}
