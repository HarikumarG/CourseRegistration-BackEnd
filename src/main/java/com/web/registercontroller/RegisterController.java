package com.web.registercontroller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;
import com.web.mailer.SmtpMailSender;
import com.web.registerdao.RegisterDao;
import com.web.registermodel.RegisterModel;

@RestController
public class RegisterController {
	@Autowired
	private SmtpMailSender smtpMailSender;
	
	@Autowired
	private RegisterDao registerdata;
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> registerform(@RequestBody RegisterModel data) throws MessagingException 
	{
		try
		{
			boolean status = registerdata.add_data(data);
			if(status)
			{	
				smtpMailSender.send(data.getEmail(),"Registration Successful","You have registered successfully");			
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson("SUCCESS");
				return ResponseEntity.ok(res);
		
			}
			else
			{
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson("FAILURE");
				return ResponseEntity.ok(res);
		
			}

		}
		catch(DataAccessException e)
		{
			String res = new GsonBuilder().disableHtmlEscaping().create().toJson("DATA ACCESS EXCEPTION OCCURED");
			return ResponseEntity.ok(res);
		}
					
	}

}
