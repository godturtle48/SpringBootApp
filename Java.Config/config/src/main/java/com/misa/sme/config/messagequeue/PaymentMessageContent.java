package com.misa.sme.config.messagequeue;

import org.json.simple.JSONObject;

import com.misa.sme.config.model.PaymentDatabaseInfo;
import com.misa.sme.config.model.PaymentDatabaseOfUser;

public class PaymentMessageContent {
	int type;
	PaymentDatabaseOfUser paymentDatabaseOfUser;
	PaymentDatabaseInfo paymentDatabaseInfo;
	public PaymentMessageContent(int type,PaymentDatabaseOfUser paymentDatabaseOfUser,
			PaymentDatabaseInfo paymentDatabaseInfo) {
		this.type=type;
		this.paymentDatabaseOfUser = paymentDatabaseOfUser;
		this.paymentDatabaseInfo = paymentDatabaseInfo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public PaymentDatabaseOfUser getPaymentDatabaseOfUser() {
		return paymentDatabaseOfUser;
	}
	public void setPaymentDatabaseOfUser(PaymentDatabaseOfUser paymentDatabaseOfUser) {
		this.paymentDatabaseOfUser = paymentDatabaseOfUser;
	}
	public PaymentDatabaseInfo getPaymentDatabaseInfo() {
		return paymentDatabaseInfo;
	}
	public void setPaymentDatabaseInfo(PaymentDatabaseInfo paymentDatabaseInfo) {
		this.paymentDatabaseInfo = paymentDatabaseInfo;
	}
	public String toString() {
		JSONObject jsonInfo = new JSONObject();
		jsonInfo.put("type",type);
		jsonInfo.put("paymentDatabaseOfUser",paymentDatabaseOfUser);
		jsonInfo.put("paymentDatabaseInfo",paymentDatabaseInfo);
		return jsonInfo.toString();
		
	}
	
}
