package com.nagarro.EmployeeRestAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.EmployeeRestAPI.model.User;
import com.nagarro.EmployeeRestAPI.service.MailService;
import com.nagarro.EmployeeRestAPI.service.RegistrationService;
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
* Description - registration controller
*/
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.ORIGIN, allowedHeaders = "*")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private MailService mail = new MailService();
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) throws Exception {
		
		String tempEmailId =user.getEmailId();
		if(tempEmailId != null && !"".equals(tempEmailId)) {
			User existingUser = service.fetchUserByEmailId(tempEmailId);
			if(existingUser != null) {
				throw new Exception("User with "+tempEmailId+" already exist");
			}
		}
		
		User userObj = null;
		userObj = service.saveUser(user);
		String mailResponse = mail.sendMail(userObj, Constants.registerHeading);
		return userObj;
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		String tempPassword = user.getPassword();
		User validUser = null;
		if(tempEmailId != null && tempPassword != null) {
			validUser = service.fetchUserByEmailIdAndPassword(tempEmailId, tempPassword);
		}
		if(validUser == null) {
			throw new Exception("Bad credentials");
		}
		return validUser;	
	}
	
	@PostMapping("/forgot")
	public void loginUser(@RequestBody String toMailID) throws Exception {
		if(toMailID != null && !"".equals(toMailID)) {
			User existingUser = service.fetchUserByEmailId(toMailID);
			if(existingUser != null) {
				String mailResponse = mail.sendMail(existingUser, Constants.forgotHeading);
			}else {
				throw new Exception("user with "+toMailID+" doesn't exist");
			}
		}
	}
}
