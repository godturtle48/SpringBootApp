package com.webencyclop.demo.model;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.webencyclop.demo.repository.RoleRepository;
import com.webencyclop.demo.service.*;

@Entity
@Table(name = "auth_role")
public class Role {
//	@Autowired
//	RoleRepository roleRepository;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "auth_role_id")
	private int id;

	@Column(name = "role_name")
	private String role;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
//
//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	} 	 
	
	
}