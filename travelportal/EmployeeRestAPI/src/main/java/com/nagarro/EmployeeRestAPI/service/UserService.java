package com.nagarro.EmployeeRestAPI.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.EmployeeRestAPI.model.Admin;
import com.nagarro.EmployeeRestAPI.model.User;
import com.nagarro.EmployeeRestAPI.repository.AdminRepository;
import com.nagarro.EmployeeRestAPI.repository.RegistrationRepository;

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
* Description - User Service
*/
@Service
public class UserService implements UserDetailsService {
    
	@Autowired
	private RegistrationRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Admin admin = adminRepository.findByEmailId(email);
    	if(admin == null) {
    		User user = userRepository.findByEmailId(email); 
    		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), new ArrayList<>());	
    	}else {
    		return new org.springframework.security.core.userdetails.User(admin.getEmailId(), admin.getPassword(), new ArrayList<>());	
    	}

    }
    
    

 

}