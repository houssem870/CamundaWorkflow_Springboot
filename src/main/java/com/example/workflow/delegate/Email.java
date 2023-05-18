package com.example.workflow.delegate;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@ComponentScan
@Component("Email")
public class Email implements JavaDelegate{

    private final JavaMailSender javaMailSender;

    @Autowired
    public Email(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String recipient = "Houssem.Abassi@hydatis.com";
        String subject = "Confirmation";
        String text = "Welcome to Camunda";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(text);

        try {
            javaMailSender.send(message);
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send email");
        }
    }
}

