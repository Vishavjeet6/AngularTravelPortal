package com.nagarro.EmployeeRestAPI.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nagarro.EmployeeRestAPI.model.User;
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
* Description - Mail Service
*/
@Service
public class MailService {
	
	@Autowired
    private JavaMailSender sender;
	
	public String sendMail(User user, String heading) {
    	String toMailID = user.getEmailId();
    	String text = "You have requested your user name and password for the your access to the Nagarro Travel Portal:\n" 
    	+ "UserName : " + user.getEmailId() + " \nPassword: " + user.getPassword()+ "\nPlease contact the Travel team if you have any questions.\n" + 
    			"Thank you\n" + 
    			"Nagarro Travel Team.\n";
    	try {
          sendEmailWithUserNamePassword(toMailID, text, heading);
          return Constants.mailSuccess;
      }catch(Exception ex) {
          return Constants.mailError+ex;
      }
    }
    
    
    private void sendEmailWithUserNamePassword(String toMailID, String text, String heading) throws MessagingException {
    	MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(toMailID);
        helper.setText(text);
        helper.setSubject(heading); 
        sender.send(message);
	}
}
