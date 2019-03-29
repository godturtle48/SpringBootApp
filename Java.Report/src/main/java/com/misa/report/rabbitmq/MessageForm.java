package com.misa.report.rabbitmq;

import java.sql.Timestamp;


import com.misa.report.model.PaymentReceipt;

public class MessageForm {
	private EventType type;
	private PaymentReceipt data;
	private Timestamp createAt=new Timestamp(System.currentTimeMillis());
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public PaymentReceipt getData() {
		return data;
	}
	public void setData(PaymentReceipt data) {
		this.data = data;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	
}
