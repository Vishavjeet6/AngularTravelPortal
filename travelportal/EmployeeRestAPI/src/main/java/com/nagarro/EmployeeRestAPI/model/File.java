package com.nagarro.EmployeeRestAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
* Description - File entity
*/
@Entity
public class File {
	
	@Id
	@Column(name = "fileId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "ticketId")
	private int ticketId;
	
	@Lob
	@Column(name = "fileByte")
	private byte[] fileByte;
	

	public File() {
		super();
	}

	public File(String name, String type, byte[] fileByte, int ticketId) {
		super();
		this.name = name;
		this.type = type;
		this.fileByte = fileByte;
		this.ticketId = ticketId;
	}

	public File(Long id, String name, String type, byte[] fileByte, int ticketId) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.fileByte = fileByte;
		this.ticketId = ticketId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}	
}
