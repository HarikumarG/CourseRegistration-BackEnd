package com.web.forgotpasswordcontroller;

import java.util.Random;

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
import com.web.forgotpassworddao.ForgotpasswordDao;
import com.web.forgotpasswordmodel.ForgotpasswordModel;
import com.web.mailer.SmtpMailSender;




@RestController
public class ForgotpasswordController {
	
	@Autowired
	private SmtpMailSender smtpMailpassword;
	
	@Autowired
	private ForgotpasswordDao forgotdata;
	
	@RequestMapping(value="/forgotpassword",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> Passworddata(@RequestBody ForgotpasswordModel data) throws MessagingException
	{
		try
		{
			String status = forgotdata.check_data(data);
			if(status!="fail")
			{	
				Random rand = new Random();
				String code = String.format("%04d", rand.nextInt(10000));
				boolean codestatus=forgotdata.add_code(code,data);
				if(codestatus)
				{
					smtpMailpassword.send(data.getEmail(),"The four digit code","The code for setting new password in IIT-Bombay-Course login is "+code);
					String res = new GsonBuilder().disableHtmlEscaping().create().toJson("SUCCESS");
					return ResponseEntity.ok(res);
				}
				else
				{
					String res = new GsonBuilder().disableHtmlEscaping().create().toJson("The code is not inserted to DataBase");
					return ResponseEntity.ok(res);
				}
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
	@RequestMapping(value="/codeverify",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> codeverify(@RequestBody ForgotpasswordModel data)
	{
		try
		{
			String status = forgotdata.check_code_data(data);
			if(status!="fail")
			{	
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
	@RequestMapping(value="/updatepassword",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updatepassword(@RequestBody ForgotpasswordModel data) 
	{
		try
		{
			boolean status = forgotdata.add_data(data);
			if(status)
			{	
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
