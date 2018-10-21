package com.oinam.tutorial.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
 
@Service
public class ApplicationMailer
{
    @Autowired
    private JavaMailSender mailSender;
     
    public void sendMail(final String mailFrom, final String mailTo, final String subject, final String mailBody)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(mailBody);
        mailSender.send(message);
        /*try {
            mailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
					message.setFrom(mailFrom);
					message.addTo(mailTo);
					message.setSubject(subject);
					message.setText(mailBody, true);
				}				
            });
        } catch (MailSendException e) {
            // your codes;
        }*/
    }
 
}
