package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class RefType {
	public RefType() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer refTypeID;

	@NotEmpty
	private String refTypeName;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="ref",fetch=FetchType.LAZY)
	private Set<PaymentReceipt> payments;
	
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
	public void setPayments(Set<PaymentReceipt> payments) {
		this.payments = payments;
	}
	public RefType(Integer refTypeID, @NotEmpty String refTypeName) {
		super();
		this.refTypeID = refTypeID;
		this.refTypeName = refTypeName;
	}
	
	
	
}
