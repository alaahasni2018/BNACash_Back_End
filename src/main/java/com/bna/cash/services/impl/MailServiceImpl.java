package com.bna.cash.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bna.cash.services.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender sender;

	@Override
	public void sendMail(String objet, String msg, String to) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(objet); 
        message.setText(msg);
        sender.send(message);
	}
}
