package com.webencyclop.demo.service;

import java.io.Console;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webencyclop.demo.model.Company;
import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.repository.CompanyRepository;
import com.webencyclop.demo.repository.RoleRepository;
import com.webencyclop.demo.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Value("${max.number.company}")
	private int maxCompanyPerDB;
	
	
	@Override
	public void saveUser(User user){
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
//		Role userRole = roleRepository.findByRole("SITE_USER");
//		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		try {
			Role userRole = roleRepository.findByRole("SITE_USER");
			if(userRole==null) {
				Role role1 = new Role();
				role1.setId(1);
				role1.setRole("SITE_USER");
				saveRole(role1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Role role1 = new Role();
			role1.setId(1);
			role1.setRole("SITE_USER");
			saveRole(role1);
		}
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	@Override
	public void saveRole(Role role) {
		// TODO Auto-generated method stub
		roleRepository.save(role);
	}
	
	@Override
	public void saveCompany(Company company, User user) {
		// TODO Auto-generated method stub
		company.setUsers(new HashSet<User>(Arrays.asList(user)));
		//total company
		int toalCompany = 0;
		try {
			toalCompany = companyRepository.countNumberCompany();
		}catch(Exception e) {
			toalCompany = 0;
		}
		
		int checkPostName = toalCompany % maxCompanyPerDB;
		int postName = (toalCompany / maxCompanyPerDB) +1;
		if(checkPostName == 0) {
			company.setDatabaseName("DatabaseMISA" + postName);
		}
		else {
			postName+=1;
			company.setDatabaseName("DatabaseMISA" + postName);
		}	
		companyRepository.save(company);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// TODO Auto-generated method stub
		User users = userRepository.findByContactEmail(user.getContactEmail());
		try {
			if(users.getContactEmail().equals("")) return false;
		}
		catch(NullPointerException e) {
			return false;
		}

		return true;
	}
	
	@Override
	public boolean isUser(String email, String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
	    try {
	    	User user = userRepository.findByContactEmail(email);
	    	System.out.println("day roi " + user.getContactEmail());
	    	if(!user.getContactEmail().equals("")) {
	    		 return encoder.matches(password, user.getPassword());
	    	}
	    	
	    }
	    catch(NullPointerException e) {
	    	System.out.println("exception" + e.toString());
	    	return false;
	    }
	   return false;
	}
}
