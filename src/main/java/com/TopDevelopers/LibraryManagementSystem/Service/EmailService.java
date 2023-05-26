package com.TopDevelopers.LibraryManagementSystem.Service;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class EmailService
{
    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String subject, String message)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); // obj creation
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }

}
