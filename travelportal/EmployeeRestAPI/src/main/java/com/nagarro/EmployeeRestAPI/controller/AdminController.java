package com.nagarro.EmployeeRestAPI.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.EmployeeRestAPI.model.Admin;
import com.nagarro.EmployeeRestAPI.service.AdminService;
import com.nagarro.EmployeeRestAPI.utils.Constants;

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
* Description - Admin Controller
*/
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.ORIGIN, allowedHeaders = "*")
public class AdminController {
	
	@Autowired
	private AdminService service;
		
	@PostMapping("/admin")
	public Admin loginUser(@RequestBody Admin admin) throws Exception {
		String tempEmailId = admin.getEmailId();
		String tempPassword = admin.getPassword();
		Admin validUser = null;
		if(tempEmailId != null && tempPassword != null) {
			validUser = service.fetchAdminByEmailIdAndPassword(tempEmailId, tempPassword);
		}
		if(validUser == null) {
			throw new Exception("Bad credentials");
		}
		return validUser;	
	}
	
	 @GetMapping("/admin/{adminId}")
	    public Optional<Admin> getUser(@PathVariable(value = "adminId") Integer adminId) {
	        return service.fetchAdminById(adminId);
	    }

}
