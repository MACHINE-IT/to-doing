package com.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements com.example.service.MailService  {

    Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    private final JavaMailSender mailSender;

    private final SimpleMailMessage simpleMailMessage;

    @Value("${spring.mail.username}")
    private final String fromEmailAddress = "";

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, SimpleMailMessage simpleMailMessage) {
        this.mailSender = mailSender;
        this.simpleMailMessage = simpleMailMessage;
    }


    @Override
    public void send(String toEmail, String subject, String body) {


        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);

        mailSender.send(simpleMailMessage);

        logger.info("mail sent successfully");

    }
}
