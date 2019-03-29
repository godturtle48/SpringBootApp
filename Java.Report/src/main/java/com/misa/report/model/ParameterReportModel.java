package com.misa.report.model;


import java.sql.Date;
import java.util.List;

public class ParameterReportModel {
	private String branch;
	private Date fromDate;
	private Date toDate;
	private String currencyID;
	private List<Integer> account;
	

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
	public List<Integer> getAccount() {
		return account;
	}
	public void setAccount(List<Integer> account) {
		this.account = account;
	}
	public ParameterReportModel(String branch, Date fromDate, Date toDate, String currencyID, List<Integer> account) {
		
		this.branch = branch;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.currencyID = currencyID;
		this.account = account;
	}
	public ParameterReportModel() {
		
	}
	
	
}
