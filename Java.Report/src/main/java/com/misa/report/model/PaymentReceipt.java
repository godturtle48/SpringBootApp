package com.misa.report.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;



public class PaymentReceipt {

	
	private String refID;
	private String keyCompany;
	private RefType ref;
	private List<InvoiceDetail> invoices;
	private  Date refDate;
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
	private Date editVersion;
	private Integer refOrdef;
	private Date createdDate;
	private String createdBy;
	private Date modifiedDate;
	private String modifiedBy;
	private Integer version;
//	/==============================================
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
	public RefType getRef() {
		return ref;
	}
	public void setRef(RefType ref) {
		this.ref = ref;
	}
	public List<InvoiceDetail> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<InvoiceDetail> invoices) {
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;}
//=======================================================>
	public PaymentReceipt() {
		super();
	}

	public String toString() {
		JSONObject obj=new JSONObject();
		obj.put("refID",refID);
		obj.put("keyCompany", keyCompany);
		obj.put("ref", ref);
		obj.put("invoices", invoices);
		obj.put("refDate", refDate);
		obj.put("postedDate", postedDate);
		obj.put("refNoFinance", refNoFinance);
		obj.put("accountObjectID", accountObjectID);
		obj.put("accountObjectName", accountObjectName);
		obj.put("accountObjectAddress", accountObjectAddress);
		obj.put("accountObjectContactName", accountObjectContactName);
		obj.put("reasonTypeID", reasonTypeID);
		obj.put("journalMemo", journalMemo);
		obj.put("description", description);
		obj.put("documentInclude", documentInclude);
		obj.put("exchangeRate", exchangeRate);
		obj.put("totalAmountOC", totalAmountOC);
		obj.put("totalAmount", totalAmount);
		obj.put("editVersion", editVersion);
		obj.put("refOrdef", refOrdef);
		obj.put("createdDate", createdDate);
		obj.put("createdBy", createdBy);
		obj.put("modifiedDate", modifiedDate);
		obj.put("modifiedBy", modifiedBy);
		obj.put("version", version);
		
		
		return obj.toJSONString();
	}
}			
