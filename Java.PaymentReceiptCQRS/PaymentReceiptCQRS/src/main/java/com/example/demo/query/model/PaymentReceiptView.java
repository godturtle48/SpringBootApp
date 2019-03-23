package com.example.demo.query.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.annotations.Index;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.command.model.InvoiceDetailCommand;
import com.example.demo.command.model.RefTypeCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * id là biến chứa id trong môngdb
 * refID là biến chứa id trong mysql
 * create Huyen 23/2
 * 
 * 
 * 
 */
@Document(collection="paymentreceipt")
public class PaymentReceiptView {

	@Id
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

//	@Indexed
	private String refID;
//	@Indexed
	private String keyCompany;

	private RefTypeCommand ref;
	
	
	private List<InvoiceDetailCommand> invoices;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date refDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date postedDate;

	private String refNoFinance;

	private Integer isPostedFinance;
	private String accountObjectID;
	private String accountObjectName;
	private String accountObjectAddress;
	private String accountObjectContactName;
	private String reasonTypeID;

	private String journalMemo;

	private String description;
	private String documentInclude;
	private Double exchangeRate;
	

	private Double totalAmountOC;

	private Double totalAmount;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date editVersion;
	
	private Integer refOrdef;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date createdDate;
	private String createdBy;

//	@Indexed
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date modifiedDate;
	private String modifiedBy;
	public String getRefID() {
		return refID;
	}
	public void setRefID(String refID) {
		this.refID = refID;
	}
	public String getKeyCompany() {
		return keyCompany;
	}
	public void setKeyCompany(String keyCompany) {
		this.keyCompany = keyCompany;
	}
	public RefTypeCommand getRef() {
		return ref;
	}
	public void setRef(RefTypeCommand ref) {
		this.ref = ref;
	}
//	@JsonIgnore
	public List<InvoiceDetailCommand> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<InvoiceDetailCommand> invoices) {
		this.invoices = invoices;
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
	public String getRefNoFinance() {
		return refNoFinance;
	}
	public void setRefNoFinance(String refNoFinance) {
		this.refNoFinance = refNoFinance;
	}
	public Integer getIsPostedFinance() {
		return isPostedFinance;
	}
	public void setIsPostedFinance(Integer isPostedFinance) {
		this.isPostedFinance = isPostedFinance;
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
	public String getReasonTypeID() {
		return reasonTypeID;
	}
	public void setReasonTypeID(String reasonTypeID) {
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
	public String getDocumentInclude() {
		return documentInclude;
	}
	public void setDocumentInclude(String documentInclude) {
		this.documentInclude = documentInclude;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Double getTotalAmountOC() {
		return totalAmountOC;
	}
	public void setTotalAmountOC(Double totalAmountOC) {
		this.totalAmountOC = totalAmountOC;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getEditVersion() {
		return editVersion;
	}
	public void setEditVersion(Date editVersion) {
		this.editVersion = editVersion;
	}
	public Integer getRefOrdef() {
		return refOrdef;
	}
	public void setRefOrdef(Integer refOrdef) {
		this.refOrdef = refOrdef;
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
	
	
	
}
