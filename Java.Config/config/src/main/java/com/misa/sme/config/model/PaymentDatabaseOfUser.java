package com.misa.sme.config.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.json.simple.JSONObject;

@Entity
public class PaymentDatabaseOfUser implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -750511557653615377L;
	//column
	@Id
	private String userId;
	@Id
	private String keycompany;
	
	@ManyToOne
	@JoinColumn(name="keydatabase",nullable = false)
	private PaymentDatabaseInfo paymentDatabaseInfo;
	
	//constructor

	public PaymentDatabaseOfUser() {
	}
	public PaymentDatabaseOfUser(String userId, String keycompany) {
		this.userId = userId;
		this.keycompany = keycompany;
	}
	public PaymentDatabaseOfUser(String userId, String keycompany, PaymentDatabaseInfo paymentDatabaseInfo) {
		this.userId = userId;
		this.keycompany = keycompany;
		this.paymentDatabaseInfo = paymentDatabaseInfo;
	}

	//setter/getter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKeycompany() {
		return keycompany;
	}
	public void setKeycompany(String keycompany) {
		this.keycompany = keycompany;
	}	
	public PaymentDatabaseInfo getPaymentDatabaseInfo() {
		return paymentDatabaseInfo;
	}
	public void setPaymentDatabaseInfo(PaymentDatabaseInfo paymentDatabaseInfo) {
		this.paymentDatabaseInfo = paymentDatabaseInfo;
	}

	public String toString() {
		JSONObject jsonInfo = new JSONObject();
		jsonInfo.put("userId",userId);
		jsonInfo.put("keycompany",keycompany);
		jsonInfo.put("keydatabase",paymentDatabaseInfo.getKeydatabase());
		return jsonInfo.toString();
		
	}
}
