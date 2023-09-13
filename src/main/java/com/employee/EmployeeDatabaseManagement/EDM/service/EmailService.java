package com.employee.EmployeeDatabaseManagement.EDM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendConfirmationEmail(String recipientEmail, String recipientType) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Welcome to Our Company");
        message.setText("Dear " + recipientType + ",\n\nWelcome to our company! We are pleased to have you on board.\n\nBest regards,\nHR Department");

        emailSender.send(message);
    }
}
