package com.misa.sme.config.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

@Entity
@Table( 
		   uniqueConstraints = {@UniqueConstraint(columnNames = {"host", "port"})}
		)
public class PaymentDatabaseServerInfo{
	//column
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	private String keyserver;
	private String host;
	private String port;	
	private String username;	
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="paymentDatabaseServerInfo")
	private Set<PaymentDatabaseInfo> listPaymentDatabaseInfo;
	
	//constructor
	public PaymentDatabaseServerInfo(String host, String port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	public PaymentDatabaseServerInfo() {
	}
	
	//setter/getter
	public String getKeyserver() {
		return keyserver;
	}
	public void setKeyserver(String keyserver) {
		this.keyserver = keyserver;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<PaymentDatabaseInfo> getListPaymentDatabaseInfo() {
		return listPaymentDatabaseInfo;
	}
	public void setListPaymentDatabaseInfo(Set<PaymentDatabaseInfo> listPaymentDatabaseInfo) {
		this.listPaymentDatabaseInfo = listPaymentDatabaseInfo;
	}
	public String toString() {
		JSONObject jsonInfo = new JSONObject();
		jsonInfo.put("host",host);
		jsonInfo.put("port",port);
		jsonInfo.put("username",username);
		jsonInfo.put("password",password);
		return jsonInfo.toString();
		
	}
}
