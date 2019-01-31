package com.webencyclop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value="select u.email FROM auth_user u WHERE u.auth_user_id = :id", nativeQuery=true)
	public List<String> findContactEmail(@Param("id")int id);	
	public User findByContactEmail(String contactEmail);
	//public User findByEmail(String contactEmail);
	public User findByContactMobile(String contactMobile);	
	
	
	
}
