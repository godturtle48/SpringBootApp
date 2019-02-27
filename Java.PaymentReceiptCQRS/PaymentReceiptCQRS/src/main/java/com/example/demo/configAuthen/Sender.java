package com.example.demo.configAuthen;

public class Sender {
	private long id=-1;
	private String email="";
	private int roleId=-1;

	public Sender(long id, String email, int roleId) {
		this.id = id;
		this.email = email;
		this.roleId = roleId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
