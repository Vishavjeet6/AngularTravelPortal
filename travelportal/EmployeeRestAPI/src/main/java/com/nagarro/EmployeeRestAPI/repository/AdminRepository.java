package com.nagarro.EmployeeRestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nagarro.EmployeeRestAPI.model.Admin;
/*
* Version info - 0.3
*
* Copyright notice - @2020 Nagarro Private Limited.
* 
* Author info - Vishavjeet Singh
*
* Creation date - 01-05-2020
*
* Last updated By - Vishavjeet Singh
*
* Last updated Date - 14-01-2020
*
* Description - Admin Repo
*/
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	public Admin findByEmailIdAndPassword(String emailId, String password);
	public Admin findByEmailId(String emailId);

}
