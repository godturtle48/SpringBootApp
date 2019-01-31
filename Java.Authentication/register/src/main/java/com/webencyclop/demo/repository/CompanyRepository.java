package com.webencyclop.demo.repository;

import java.util.List;
import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.form.CompanyModel;
import com.webencyclop.demo.model.Company;



@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
//	@Query(value="Select * from company c inner join user_company u ON c.ma_so_thue = u.ma_so_thue"
//			+ "inner join user a ON u.auth_user_id = a.auth_user_id where a.email = :email", nativeQuery=true)
	@Query(value="select c.* from company c, user_company u "
				+ "WHERE c.tax_number = u.tax_number "
				+ "AND u.auth_user_id = :id", nativeQuery = true)
	public List<Company> findByCompanyTaxNumber(@Param("id") int id);
	
	public Company findByCompanyTaxNumber(String companyTaxNumber);
	
	@Query(value="select COUNT(*) from Company", nativeQuery = true)
	public int countNumberCompany();
}
