package com.misa.sme.config.model;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

@Entity
public class PaymentDatabaseInfo{	
	//column
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	private String keydatabase;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="paymentDatabaseInfo")
	private Set<PaymentDatabaseOfUser> listPaymentDatabaseOfUser;
	
	@ManyToOne
	@JoinColumn(name="keyserver",nullable = false)
	private PaymentDatabaseServerInfo paymentDatabaseServerInfo;
	
	//constructor
	public PaymentDatabaseInfo(PaymentDatabaseServerInfo paymentDatabaseServerInfo) {
		this.paymentDatabaseServerInfo = paymentDatabaseServerInfo;
	}
	public PaymentDatabaseInfo() {
	}	
	
	//setter/getter
	public String getKeydatabase() {
		return keydatabase;
	}
	public void setKeydatabase(String keydatabase) {
		this.keydatabase = keydatabase;
	}
	public PaymentDatabaseServerInfo getPaymentDatabaseServerInfo() {
		return paymentDatabaseServerInfo;
	}
	public void setPaymentDatabaseServerInfo(PaymentDatabaseServerInfo paymentDatabaseServerInfo) {
		this.paymentDatabaseServerInfo = paymentDatabaseServerInfo;
	}	
	public Set<PaymentDatabaseOfUser> getListPaymentDatabaseOfUser() {
		return listPaymentDatabaseOfUser;
	}
	public void setListPaymentDatabaseOfUser(Set<PaymentDatabaseOfUser> listPaymentDatabaseOfUser) {
		this.listPaymentDatabaseOfUser = listPaymentDatabaseOfUser;
	}
	public String toString() {
		JSONObject jsonInfo = new JSONObject();
		jsonInfo.put("keydatabase",keydatabase);
		jsonInfo.put("host",paymentDatabaseServerInfo.getHost());
		jsonInfo.put("port",paymentDatabaseServerInfo.getPort());
		jsonInfo.put("username",paymentDatabaseServerInfo.getUsername());
		jsonInfo.put("password",paymentDatabaseServerInfo.getPassword());
		return jsonInfo.toString();		
	}

	
}