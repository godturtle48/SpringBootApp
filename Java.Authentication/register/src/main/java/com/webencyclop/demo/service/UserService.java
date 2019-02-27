package com.webencyclop.demo.service;

import java.lang.reflect.InvocationTargetException;

import com.webencyclop.demo.model.Company;
import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
public interface UserService {
	public void saveUser(User user);
	public void saveRole(Role role);
	//public void saveRole(User user);
	public void saveCompany(Company company, User user);
	
	public boolean isUserAlreadyPresent(User user); 
	public boolean isUser(String email, String password);
}
