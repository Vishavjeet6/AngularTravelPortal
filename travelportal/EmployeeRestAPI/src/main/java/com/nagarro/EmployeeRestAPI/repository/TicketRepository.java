package com.nagarro.EmployeeRestAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.EmployeeRestAPI.model.Ticket;
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
* Description - Ticket Repo
*/
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	Page<Ticket> findByUserId(Integer userId, Pageable pageable);
	Optional<Ticket> findByIdAndUserId(Integer ticketId, Integer userId);
	List<Ticket> findByStatusNot(String status);
  
    
}
