package com.example.journalapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServices {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMain(String to, String subject, String body) {

        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);

            javaMailSender.send(mail);
        }catch (Exception e) {
            System.out.print("Email not send yet.");
        }
    }
}
