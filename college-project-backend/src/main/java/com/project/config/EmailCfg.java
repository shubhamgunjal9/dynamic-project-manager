package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Component
public class EmailCfg {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendMail(String toMail,
                       String subject,
                       String body) {

    SimpleMailMessage message=new SimpleMailMessage();
    message.setFrom("innership45@gmail.com");
    message.setTo(toMail);
    message.setText(body);
    message.setSubject(subject);

    javaMailSender.send(message);

    System.out.println("mail sent succesfully....");
  }
}
