package com.nagarro.EmployeeRestAPI.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.EmployeeRestAPI.model.File;
import com.nagarro.EmployeeRestAPI.repository.FileRepository;
import com.nagarro.EmployeeRestAPI.repository.TicketRepository;
import com.nagarro.EmployeeRestAPI.utils.Constants;
import com.nagarro.EmployeeRestAPI.utils.ResourceNotFoundException;
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
* Description - file controller
*/
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = Constants.ORIGIN, allowedHeaders = "*")
public class FileController {
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private TicketRepository ticketRepository;

	@PostMapping("uploadfile/{ticketId}")
	public void uploadFile(@PathVariable("ticketId") Integer ticketId,
			@RequestParam("file") List<MultipartFile> files) throws IOException {
		if(!ticketRepository.findById(ticketId).isPresent()) {
			throw new ResourceNotFoundException("TicketId "+ ticketId + " not found");
		}
		for(MultipartFile file : files){
			File img = new File(file.getOriginalFilename(), 
					file.getContentType(), 
					file.getBytes(),
					ticketId);
			fileRepository.save(img);		
		}
	}
	
	@GetMapping(path = { "/getfile/{ticketId}" })
	public List<File> getAllFile(@PathVariable("ticketId") int ticketId) throws IOException {
		if(!ticketRepository.findById(ticketId).isPresent()) {
			throw new ResourceNotFoundException("TicketId "+ ticketId + " not found");
		}
		
		return fileRepository.findByTicketId(ticketId);
	}
	
	@GetMapping(path = { "/getfile/{ticketId}/{fileId}" })
	public Optional<File> getFile(@PathVariable("ticketId") int ticketId,
			@PathVariable("fileId") Long fileId) throws IOException {
		if(!ticketRepository.findById(ticketId).isPresent()) {
			throw new ResourceNotFoundException("TicketId "+ ticketId + " not found");
		}
		return fileRepository.findById(fileId);	
	}
}
