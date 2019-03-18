package com.misa.report.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="GeneralLedger")
public class GeneralLedger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="GeneralLedgerID")
	private int GeneralLedgerID;
	@NotNull
	private int RefID;
	private int RefDetailID;
	private int RefType;
	private String RefNo;
	@NotNull
	private Date RefDate;
	@NotNull
	private Date PostedDate;
	private String CurrencyID;
	private Double ExchangeRate;
	@NotNull
	private Double AmountOC;
	@NotNull
	private Double Amount;
	private String JournalMemo;
	private String Description;
	private String ContactName;
	private Integer AccountObjectID;
	private String AccountObjectName;
	
	
	public int getGeneralLedgerID() {
		return GeneralLedgerID;
	}
	public void setGeneralLedgerID(int generalLedgerID) {
		GeneralLedgerID = generalLedgerID;
	}
	public int getRefID() {
		return RefID;
	}
	public void setRefID(int refID) {
		RefID = refID;
	}
	public int getRefDetailID() {
		return RefDetailID;
	}
	public void setRefDetailID(int refDetailID) {
		RefDetailID = refDetailID;
	}
	public int getRefType() {
		return RefType;
	}
	public void setRefType(int refType) {
		RefType = refType;
	}
	public String getRefNo() {
		return RefNo;
	}
	public void setRefNo(String refNo) {
		RefNo = refNo;
	}
	public Date getRefDate() {
		return RefDate;
	}
	public void setRefDate(Date refDate) {
		RefDate = refDate;
	}
	public Date getPostedDate() {
		return PostedDate;
	}
	public void setPostedDate(Date postedDate) {
		PostedDate = postedDate;
	}
	public String getCurrencyID() {
		return CurrencyID;
	}
	public void setCurrencyID(String currencyID) {
		CurrencyID = currencyID;
	}
	public Double getExchangeRate() {
		return ExchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		ExchangeRate = exchangeRate;
	}
	public Double getAmountOC() {
		return AmountOC;
	}
	public void setAmountOC(Double amountOC) {
		AmountOC = amountOC;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public String getJournalMemo() {
		return JournalMemo;
	}
	public void setJournalMemo(String journalMemo) {
		JournalMemo = journalMemo;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getContactName() {
		return ContactName;
	}
	public void setContactName(String contactName) {
		ContactName = contactName;
	}
	public Integer getAccountObjectID() {
		return AccountObjectID;
	}
	public void setAccountObjectID(Integer accountObjectID) {
		AccountObjectID = accountObjectID;
	}
	public String getAccountObjectName() {
		return AccountObjectName;
	}
	public void setAccountObjectName(String accountObjectName) {
		AccountObjectName = accountObjectName;
	}
	
	@Override
	public String toString() {
		return "GeneralLedger [GeneralLedgerID=" + GeneralLedgerID + ", RefID=" + RefID + ", RefDetailID=" + RefDetailID
				+ ", RefType=" + RefType + ", RefNo=" + RefNo + ", RefDate=" + RefDate + ", PostedDate=" + PostedDate
				+ ", CurrencyID=" + CurrencyID + ", ExchangeRate=" + ExchangeRate + ", AmountOC=" + AmountOC
				+ ", Amount=" + Amount + ", JournalMemo=" + JournalMemo + ", Description=" + Description
				+ ", ContactName=" + ContactName + ", AccountObjectID=" + AccountObjectID + ", AccountObjectName="
				+ AccountObjectName + "]";
	}
	
	
}
