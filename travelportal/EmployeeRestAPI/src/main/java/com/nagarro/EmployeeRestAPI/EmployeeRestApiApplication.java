package com.nagarro.EmployeeRestAPI;

/*
* Class name - EmployeeRestAPI
*
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
* Description - springboot application
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@SpringBootApplication
@EnableJpaAuditing
public class EmployeeRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRestApiApplication.class, args);
	}

}
