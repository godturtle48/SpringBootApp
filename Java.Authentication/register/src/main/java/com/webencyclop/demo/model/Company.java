package com.webencyclop.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.id.GUIDGenerator;

@Entity
@Table(name="Company")
public class Company {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tax_number")
	private String companyTaxNumber; 
	 
	@Column(name="Company_name")
	private String companyName;
	
	@Column(name="address", columnDefinition="nvarchar(100)")
	private String address;
	
	@Column(name="database_name")
	private String databaseName;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable( 
			name="user_company",
			joinColumns = @JoinColumn(name="tax_number"),
			inverseJoinColumns = @JoinColumn(name="auth_user_id")	
	)
	private Set<User> users = new HashSet<>();



	public String getCompanyTaxNumber() {
		return companyTaxNumber;
	}

	public void setCompanyTaxNumber(String companyTaxNumber) {
		this.companyTaxNumber = companyTaxNumber;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public Set<User> getUsers() {
//		return users;
//	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
}
