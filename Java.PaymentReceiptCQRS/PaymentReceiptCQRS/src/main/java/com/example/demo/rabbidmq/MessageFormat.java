package com.example.demo.rabbidmq;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

import com.example.demo.command.model.PaymentReceiptCommand;
/* 
 * Created by Huyen 22/2 format du lieu gui qua message queue
 */
public class MessageFormat {

	private EventType type;
	private PaymentReceiptCommand data;
	private Timestamp createAt=new Timestamp(System.currentTimeMillis());
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public PaymentReceiptCommand getData() {
		return data;
	}
	public void setData(PaymentReceiptCommand data) {
		this.data = data;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	public MessageFormat(EventType type, PaymentReceiptCommand data) {
		super();
		this.type = type;
		this.data = data;
		this.createAt = new Timestamp(System.currentTimeMillis());
	}
	public MessageFormat() {
		
	}
	@Override
	public String toString() {
		JSONObject message=new  JSONObject();
		message.put("type", type);
		message.put("data", data);
		message.put("createAt", createAt.toString());
		return message.toJSONString();
	}
}
