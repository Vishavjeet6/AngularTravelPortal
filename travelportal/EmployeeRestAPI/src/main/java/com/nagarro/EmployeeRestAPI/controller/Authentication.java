package com.nagarro.EmployeeRestAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.EmployeeRestAPI.model.AuthRequest;
import com.nagarro.EmployeeRestAPI.service.AdminService;
import com.nagarro.EmployeeRestAPI.service.RegistrationService;
import com.nagarro.EmployeeRestAPI.utils.Constants;
import com.nagarro.EmployeeRestAPI.utils.JwtUtil;

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
* Description - Authentication Controller 
*/
@RestController
@CrossOrigin(origins = Constants.ORIGIN, allowedHeaders = "*")
public class Authentication {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RegistrationService registrationService;
	@Autowired
	private AdminService adminService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome Vishavjeet !!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        
        return jwtUtil.generateToken(authRequest.getUserName()
                , registrationService.fetchUserByEmailId(authRequest.getUserName()).getId());
    }
    
    @PostMapping("/authenticateAdmin")
    public String generateAdminToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName()
                , adminService.fetchAdminByEmailIdAndPassword(authRequest.getUserName(), authRequest.getPassword()).getId());
    }
}