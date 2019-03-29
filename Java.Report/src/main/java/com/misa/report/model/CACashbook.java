package com.misa.report.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection="cashbook")
public class CACashbook {
	@Id
	private String cashbookID;// đã ghi sổ hay chưa---> xem cần ko??????
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date cashbookPostedDate; // ngày ghi sổ
	private Integer isPostedManagement;
	@NotNull
	private String keyCompany;
	@NotNull
	private String refID;	
	private int refType;// ví dụ 1010 ,1020

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date refDate;	// ngày chứng từ
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date postedDate;	// ngày hạch toán

	private String refNo; // số chứng từ

	private String currencyID;	// loại tiền
	private Double exchangeRate;	//tỉ số đối hoái
	@NotNull
	private Double paymentAmountOC; // tổng chi, tiền loại gốc
	@NotNull
	private Double paymentAmount;
	@NotNull
	private Double recieptAmountOC; // tổng nhận, tiền loại gốc
	@NotNull
	private Double recieptAmount;
	@NotNull
	private Double signTotalAmountOC;//xử dụng tính tiền tồn
	@NotNull
	private Double signTotalAmount;
	
	private String journalMemo; // lý do 
	private String description; // diễn giải chi tiết
	private String accountObjectID; 
	private String accountObjectName;
	private String accountObjectAddress;
	private String contactName; // người gửi/ nhận
	private String documentInclude;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date createdDate;
	private String createdBy;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date modifiedDate;
	private String modifiedBy;
	private List<GeneralLedger> generalLedgersList ;
	
	
	public CACashbook() {}
	
	public CACashbook( Date cashbookPostedDate, Integer isPostedManagement, @NotNull String keycompany,
			@NotNull String refID, int refType, String refTypeName, String refNo, Date refDate, Date postedDate,
			String currencyID, Double exchangeRate, Double paymentAmountOC, Double paymentAmount,
			Double recieptAmountOC, Double recieptAmount, String journalMemo, String description,
			String accountObjectID, String accountObjectName, String accountObjectAddress, String contactName,
			String documentInclude, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
	
		this.cashbookPostedDate = cashbookPostedDate;
		this.isPostedManagement = isPostedManagement;
		this.keyCompany = keycompany;
		this.refID = refID;
		this.refType = refType;

		this.refNo = refNo;
		this.refDate = refDate;
		this.postedDate = postedDate;
		this.currencyID = currencyID;
		this.exchangeRate = exchangeRate;
		this.paymentAmountOC = paymentAmountOC;
		this.paymentAmount = paymentAmount;
		this.recieptAmountOC = recieptAmountOC;
		this.recieptAmount = recieptAmount;
		this.journalMemo = journalMemo;
		this.description = description;
		this.accountObjectID = accountObjectID;
		this.accountObjectName = accountObjectName;
		this.accountObjectAddress = accountObjectAddress;
		this.contactName = contactName;
		this.documentInclude = documentInclude;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}
	public String getCashbookID() {
		return cashbookID;
	}
	public void setCashbookID(String cashbookID) {
		cashbookID =cashbookID;
	}
	public Date getCashbookPostedDate() {
		return cashbookPostedDate;
	}
	public void setCashbookPostedDate(Date cashbookPostedDate) {
		this.cashbookPostedDate = cashbookPostedDate;
	}
	public Integer isPostedManagement() {
		return isPostedManagement;
	}
	public void setPostedManagement(Integer isPostedManagement) {
		this.isPostedManagement = isPostedManagement;
	}
	public String getKeycompany() {
		return keyCompany;
	}
	public void setKeycompany(String keycompany) {
		this.keyCompany = keycompany;
	}
	public String getRefID() {
		return refID;
	}
	public void setRefID(String string) {
		this.refID = string;
	}
	public int getRefType() {
		return refType;
	}
	public void setRefType(int refType) {
		this.refType = refType;
	}

	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public Date getRefDate() {
		return refDate;
	}
	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public String getCurrencyID() {
		return currencyID;
	}
	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Double getPaymentAmountOC() {
		return paymentAmountOC;
	}
	public void setPaymentAmountOC(Double paymentAmountOC) {
		this.paymentAmountOC = paymentAmountOC;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Double getRecieptAmountOC() {
		return recieptAmountOC;
	}
	public void setRecieptAmountOC(Double recieptAmountOC) {
		this.recieptAmountOC = recieptAmountOC;
	}
	public Double getRecieptAmount() {
		return recieptAmount;
	}
	public void setRecieptAmount(Double recieptAmount) {
		this.recieptAmount = recieptAmount;
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
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getDocumentInclude() {
		return documentInclude;
	}
	public void setDocumentInclude(String documentInclude) {
		this.documentInclude = documentInclude;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public List<GeneralLedger> getGeneralLedgersList() {
		return generalLedgersList;
	}
	public void setGeneralLedgersList(List<GeneralLedger> generalLedgersList) {
		this.generalLedgersList = generalLedgersList;
	}
	
	
	
	public Double getSignTotalAmountOC() {
		return signTotalAmountOC;
	}

	public void setSignTotalAmountOC(Double signTotalAmountOC) {
		this.signTotalAmountOC = signTotalAmountOC;
	}


	public Integer getIsPostedManagement() {
		return isPostedManagement;
	}

	public void setIsPostedManagement(Integer isPostedManagement) {
		this.isPostedManagement = isPostedManagement;
	}

	public String getKeyCompany() {
		return keyCompany;
	}

	public void setKeyCompany(String keyCompany) {
		this.keyCompany = keyCompany;
	}

	public Double getSignTotalAmount() {
		return signTotalAmount;
	}

	public void setSignTotalAmount(Double signTotalAmount) {
		this.signTotalAmount = signTotalAmount;
	}

	@Override
	public String toString() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("cashbookID",cashbookID );
		map.put("cashbookPostedDate",cashbookPostedDate);	
		map.put("isPostedManagement", isPostedManagement);
		map.put("keycompany", keyCompany);	
		map.put("refID", refID);
		map.put("refType", refType);	
		
		map.put("refNo", refNo);	
		map.put("refDate", refDate);
		map.put("postedDate", postedDate);	
		map.put("postedDate", postedDate);
		map.put("exchangeRate", exchangeRate);	
		map.put("paymentAmountOC", paymentAmountOC);
		map.put("paymentAmount", paymentAmount);	
		map.put("recieptAmountOC", recieptAmountOC);
		map.put("recieptAmount", recieptAmount);
		map.put("journalMemo", journalMemo);
		map.put("description", description);
		map.put("accountObjectID", accountObjectID);
		map.put("accountObjectName", accountObjectName);
		map.put("accountObjectAddress", accountObjectAddress);
		map.put("contactName", contactName);
		map.put("documentInclude", documentInclude);
		map.put("createdDate", createdDate);
		map.put("createdBy", createdBy);
		map.put("modifiedDate", modifiedDate);
		map.put("modifiedBy", modifiedBy);
		return map.toString();
	}
}
