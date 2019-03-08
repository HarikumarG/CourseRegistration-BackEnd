package com.web.mailer;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service("mailService")
public class SmtpMailSender {
	@Autowired
	private MailSender mailSender;
	public void send(String to,String subject,String body) throws MessagingException
	{
			Thread t= new Thread(new Runnable(){
				
			@Override
			public void run(){
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(to);
				message.setSubject(subject);
				message.setText(body);
				mailSender.send(message);
			}
		});
		t.start();
	}

}


