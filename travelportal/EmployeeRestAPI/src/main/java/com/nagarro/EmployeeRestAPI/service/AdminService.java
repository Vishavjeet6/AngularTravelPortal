package com.nagarro.EmployeeRestAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.EmployeeRestAPI.model.Admin;
import com.nagarro.EmployeeRestAPI.repository.AdminRepository;
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
* Description - Admin Service
*/
@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repo;

	public Admin fetchAdminByEmailIdAndPassword(String emailId, String password) {
		return repo.findByEmailIdAndPassword(emailId, password);
	}
	
	public Optional<Admin> fetchAdminById(int adminId) {
		return repo.findById(adminId);
	}
	
	public Admin fetchAdminByEmailId(String emailId) {
		return repo.findByEmailId(emailId);
	}
	

}
