package com.nagarro.EmployeeRestAPI.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
* Description - Ticket entity
*/
@Entity
public class Ticket extends AuditModel {
	@Id
	@Column(name = "ticketId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "requestType", nullable = false)
	private String requestType;
	
	@Column(name = "priority", nullable = false)
	private String priority;
	
	@Column(name = "travelCity", nullable = false)
	private String travelCity;
	
	@Column(name = "fromCity", nullable = false)
	private String fromCity;
	
	@Column(name = "startDate", nullable = false)
	private Date startDate;
	
	@Column(name = "endDate", nullable = false)
	private Date endDate;
	
	@Column(name = "passportNumber", nullable = false, length=25)
	private String passportNumber;
	
	@Column(name = "projectName", nullable = false, length=100)
	private String projectName;
	
	@Column(name = "expenseBourneBy", nullable = false)
	private String expenseBourneBy;
	
	@Column(name = "travelApprover", nullable = true, length=100)
	private String travelApprover;
	
	@Column(name = "expectedDuration", nullable = true, length=100)
	private String expectedDuration;
	
	@Column(name = "upperBound", nullable = true, length=500)
	private String upperBound;
	
	@Column(name = "additionalDetails", nullable = true, length=1000)
	private String additionalDetails;
	                        
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "commentByAdmin", nullable = true)
    private String commentByAdmin;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

	public Ticket() {
		super();
	}

	public Ticket(int id, String requestType, String priority, String travelCity, String fromCity, Date startDate,
			Date endDate, String passportNumber, String projectName, String expenseBourneBy, String travelApprover,
			String expectedDuration, String upperBound, String additionalDetails, String status,
			String commentByAdmin) {
		super();
		this.id = id;
		this.requestType = requestType;
		this.priority = priority;
		this.travelCity = travelCity;
		this.fromCity = fromCity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.passportNumber = passportNumber;
		this.projectName = projectName;
		this.expenseBourneBy = expenseBourneBy;
		this.travelApprover = travelApprover;
		this.expectedDuration = expectedDuration;
		this.upperBound = upperBound;
		this.additionalDetails = additionalDetails;
		this.status = status;
		this.commentByAdmin = commentByAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTravelCity() {
		return travelCity;
	}

	public void setTravelCity(String travelCity) {
		this.travelCity = travelCity;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getExpenseBourneBy() {
		return expenseBourneBy;
	}

	public void setExpenseBourneBy(String expenseBourneBy) {
		this.expenseBourneBy = expenseBourneBy;
	}

	public String getTravelApprover() {
		return travelApprover;
	}

	public void setTravelApprover(String travelApprover) {
		this.travelApprover = travelApprover;
	}

	public String getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(String expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	public String getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommentByAdmin() {
		return commentByAdmin;
	}

	public void setCommentByAdmin(String commentByAdmin) {
		this.commentByAdmin = commentByAdmin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}