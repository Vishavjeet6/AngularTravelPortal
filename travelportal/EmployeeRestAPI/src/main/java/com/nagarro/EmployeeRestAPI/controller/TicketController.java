package com.nagarro.EmployeeRestAPI.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.EmployeeRestAPI.model.Ticket;
import com.nagarro.EmployeeRestAPI.repository.TicketRepository;
import com.nagarro.EmployeeRestAPI.repository.UserRepository;
import com.nagarro.EmployeeRestAPI.utils.Constants;
import com.nagarro.EmployeeRestAPI.utils.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
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
* Description - ticket controller
*/
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.ORIGIN, allowedHeaders = "*")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository; 
    
    @GetMapping("/admin/tickets")
    public List<Ticket> getNotApprovedTickets() throws ResourceNotFoundException{
    	return ticketRepository.findAll();
    }
    
    @GetMapping("/admin/tickets/{status}")
    public List<Ticket> getAllTickets(
    		@PathVariable (value = "status") String status ) throws ResourceNotFoundException{
    	return ticketRepository.findByStatusNot(status);
    }
    
    
    @GetMapping("/users/{userId}/tickets")
    public Page<Ticket> getAllTicketsByUserId(
    		@PathVariable (value = "userId") Integer userId, 
    		@RequestParam("page") int page, 
    		@RequestParam("size")int size) throws ResourceNotFoundException{
        if(!userRepository.findById(userId).isPresent()) {
        	throw new ResourceNotFoundException("UserId "+ userId + " not found");
        }
    	return ticketRepository.findByUserId(userId, PageRequest.of(page, size));
    }
    
    @GetMapping("/users/{userId}/tickets/{ticketId}")
    public Optional<Ticket> getTicketsByTicketIdAndUserId(@PathVariable (value = "userId") Integer userId,
            @PathVariable (value = "ticketId") Integer ticketId) {
        return ticketRepository.findByIdAndUserId(ticketId, userId);
    }
    

    @PostMapping("/users/{userId}/tickets")
    public Ticket createTicket(@PathVariable (value = "userId") Integer userId,
                                 @Valid @RequestBody Ticket ticket) {
        return userRepository.findById(userId).map(user -> {
            ticket.setUser(user);
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/tickets/{ticketId}")
    public Ticket updateTicket(@PathVariable (value = "userId") Integer userId,
                                 @PathVariable (value = "ticketId") Integer ticketId,
                                 @Valid @RequestBody Ticket ticket) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }
        

        return ticketRepository.findById(ticketId).map(comment -> {
            comment.setRequestType(ticket.getRequestType());
            comment.setPriority(ticket.getPriority());
            comment.setTravelCity(ticket.getTravelCity());
            comment.setFromCity(ticket.getFromCity());
            comment.setStartDate(ticket.getStartDate());
            comment.setEndDate(ticket.getEndDate());
            comment.setCommentByAdmin(ticket.getCommentByAdmin());
            comment.setPassportNumber(ticket.getPassportNumber());
            comment.setProjectName(ticket.getProjectName());
            comment.setExpenseBourneBy(ticket.getExpenseBourneBy());
            comment.setTravelApprover(ticket.getTravelApprover());
            comment.setExpectedDuration(ticket.getExpectedDuration());
            comment.setStatus(ticket.getStatus());
            comment.setUpperBound(ticket.getUpperBound());
            comment.setAdditionalDetails(ticket.getAdditionalDetails());
            return ticketRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("TicketId " + ticketId + "not found"));
    }
    
    @DeleteMapping("/users/{userId}/tickets/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable (value = "userId") Integer userId,
                              @PathVariable (value = "ticketId") Integer ticketId) {
        return ticketRepository.findByIdAndUserId(ticketId, userId).map(comment -> {
            ticketRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id " + ticketId + " and UserId " + userId));
    }
}