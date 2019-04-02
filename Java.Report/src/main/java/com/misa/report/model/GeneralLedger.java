package com.misa.report.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection="generalLedger")
public class GeneralLedger {
	// Sổ chi tiết tiền mặt
	@Id
	private String generalLedgerID;
	@NotNull
	private String keyCompany;
	@Indexed
	@NotNull
	private String refID;
	@Indexed
	@NotNull
	private String refDetailID;
	@NotNull
	private Integer refType;
	@NotNull
	private String refNo; // số chứng từ
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date refDate;	// ngày chứng từ
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date postedDate;	// ngày hạch toán
	private String currencyID;	// loại tiền
	private Double exchangeRate;	//tỉ số đối hoái
	private Double debitAmountOC;	// nợ, tiền gốc VNĐ, USD,...
	private Double debitAmount;	//nợ, tiền việt= tiền gốc* tỷ số đối hoái
	private Double creditAmountOC;	//có , tiền gốc VNĐ, USD,...
	private Double creditAmount;	//có, tiền việt= tiền gốc* tỷ số đối hoái
	private String description;		// diễn giải chi tiết
	private String contactName;		//người nhận hoặc người nộp
	private Integer accountNumber;	// số tài khoản
	private Integer correspondingAccountNumber; // số tài khoản đối ứng	
	
	public String getKeyCompany() {
		return keyCompany;
	}

	public void setKeyCompany(String keyCompany) {
		this.keyCompany = keyCompany;
	}

	public void setRefType(Integer refType) {
		this.refType = refType;
	}

	public GeneralLedger() {}
	
	public GeneralLedger(String generalLedgerID, @NotNull String keycompany, @NotNull String refID, String refDetailID,
			int refType, String refNo, Date refDate, Date postedDate, String currencyID, Double exchangeRate,
			Double debitAmountOC, Double debitAmount, Double creditAmountOC, Double creditAmount, String description,
			String contactName, Integer accountNumber, Integer correspondingAccountNumber) {
		this.generalLedgerID = generalLedgerID;
		this.keyCompany = keycompany;
		this.refID = refID;
		this.refDetailID = refDetailID;
		this.refType = refType;
		this.refNo = refNo;
		this.refDate = refDate;
		this.postedDate = postedDate;
		this.currencyID = currencyID;
		this.exchangeRate = exchangeRate;
		this.debitAmountOC = debitAmountOC;
		this.debitAmount = debitAmount;
		this.creditAmountOC = creditAmountOC;
		this.creditAmount = creditAmount;
		this.description = description;
		this.contactName = contactName;
		this.accountNumber = accountNumber;
		this.correspondingAccountNumber = correspondingAccountNumber;
	}

	public String getGeneralLedgerID() {
		return generalLedgerID;
	}
	public void setGeneralLedgerID(String generalLedgerID) {
		this.generalLedgerID = generalLedgerID;
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
	public void setRefID(String refID) {
		this.refID = refID;
	}
	public String getRefDetailID() {
		return refDetailID;
	}
	public void setRefDetailID(String string) {
		this.refDetailID = string;
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
	public Double getDebitAmountOC() {
		return debitAmountOC;
	}
	public void setDebitAmountOC(Double debitAmountOC) {
		this.debitAmountOC = debitAmountOC;
	}
	public Double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}
	public Double getCreditAmountOC() {
		return creditAmountOC;
	}
	public void setCreditAmountOC(Double creditAmountOC) {
		this.creditAmountOC = creditAmountOC;
	}
	public Double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getCorrespondingAccountNumber() {
		return correspondingAccountNumber;
	}
	public void setCorrespondingAccountNumber(Integer correspondingAccountNumber) {
		correspondingAccountNumber = correspondingAccountNumber;
	}

	
	@Override
	public String toString() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("generalLedgerID", generalLedgerID);
		map.put("keycompany", keyCompany);
		map.put("refID", refID);
		map.put("refDetailID", refDetailID);
		map.put("refType", refType);
		map.put("refNo", refNo);
		map.put("refDate", refDate);
		map.put("postedDate", postedDate);
		map.put("currencyID", currencyID);
		map.put("exchangeRate", exchangeRate);
		map.put("debitAmountOC", debitAmountOC);
		map.put("debitAmount", debitAmount);
		map.put("creditAmountOC", creditAmountOC);
		map.put("creditAmount", creditAmount);
		map.put("description", description);
		map.put("contactName", contactName);
		map.put("accountNumber", accountNumber);
		map.put("correspondingAccountNumber", correspondingAccountNumber);	
		return map.toString();
	}

}
