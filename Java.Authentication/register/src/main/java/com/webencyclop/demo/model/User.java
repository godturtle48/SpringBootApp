//package com.webencyclop.demo.model;
//
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "auth_user")
//public class User {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "auth_user_id")
//	private int id;
//	
//	@NotNull(message="Phone Number is required")
//	@Column(name="contactMobile")
//	private String contactMobile;
//	
////	@NotNull(message="first name is required")
////	@Column(name = "first_name")
////	private String name;
////
////	@NotNull(message="Last name is required")
////	@Column(name = "last_name")
////	private String lastName;
//
//	@NotNull(message="ContactEmail is required")
//	@Column(name = "contactEmail")
//	private String contactEmail;
//
//	@NotNull(message="password name is required")
//	@Column(name = "password")
//	private String password;
//
//	@Column(name = "status")
//	private String status;
//
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
//	private Set<Role> roles;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getContactMobile() {
//		return contactMobile;
//	}
//
//	public void setContactMobile(String contactMobile) {
//		this.contactMobile = contactMobile;
//	}
//
//	public String getContactEmail() {
//		return contactEmail;
//	}
//
//	public void setContactEmail(String contactEmail) {
//		this.contactEmail = contactEmail;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
//	
//
//}



package com.webencyclop.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auth_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_user_id")
	private int id;
	
	@NotNull(message="phone number is required")
	@Column(name = "contactMobile")
	private String contactMobile;


//	@NotNull(message="email is required")
//	@Column(name = "contactemail")
//	private String contactEmail;

	@NotNull(message="email is required")
	@Column(name = "email")
	private String contactEmail;
	
	@NotNull(message="password name is required")
	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;
	
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="users")
	private Set<Company> companies; 

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> conpanies) {
		this.companies = companies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

//	public String getContactEmail() {
//		return contactEmail;
//	}
//
//	public void setContactEmail(String contactEmail) {
//		this.contactEmail = contactEmail;
//	}
	

	public String getPassword() {
		return password;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

}
