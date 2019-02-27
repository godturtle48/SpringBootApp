package com.example.demo.command.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.json.simple.JSONObject;

@Entity
public class RefTypeCommand {

	
	public RefTypeCommand() {
		super();
	}
	@Id
	private Integer refTypeID;

	@NotEmpty
	private String refTypeName;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="ref",fetch=FetchType.LAZY)
	private Set<PaymentReceiptCommand> payments;
	
	/////////////////======================================
	
	public Integer getRefTypeID() {
		return refTypeID;
	}
	public void setRefTypeID(Integer refTypeID) {
		this.refTypeID = refTypeID;
	}
	public String getRefTypeName() {
		return refTypeName;
	}
	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}
//	public Set<PaymentReceipt> getPayments() {
//		return payments;
//	}
	public void setPayments(Set<PaymentReceiptCommand> payments) {
		this.payments = payments;
	}
	public RefTypeCommand(Integer refTypeID, @NotEmpty String refTypeName) {
		super();
		this.refTypeID = refTypeID;
		this.refTypeName = refTypeName;
	}
	
	public String toString() {
		JSONObject obj=new JSONObject();
		obj.put("refTypeID",refTypeID);
		obj.put("refTypeName", refTypeName);
		return obj.toJSONString();
	}
	
}
