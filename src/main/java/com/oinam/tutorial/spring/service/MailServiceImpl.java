package com.oinam.tutorial.spring.service;

import java.nio.charset.StandardCharsets;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
    JavaMailSender mailSender;
 
	@Override
	public void sendEmail(String recipientAddress, String subject, String message) {
		
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
 
        try {
            mailSender.send(email);
            System.out.println("Message Send...");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
		
	}
	 
    @Override
    public void sendEmail(String [] recipientAddress,String []bcc,String [] cc, String subject, String message) {
 
    	// creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setBcc(bcc);
        email.setCc(cc);
        email.setSubject(subject);
        email.setText(message);
 
        try {
            mailSender.send(email);
            System.out.println("Message Send...");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

	public void sendSimpleMessage(final String mailFrom, final String mailTo, final String subject,
			final String mailBody) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));
		String inlineImage = "<img src=\"cid:logo.png\"></img><br/>";

		helper.setText(inlineImage + mailBody, true);
		helper.setSubject(subject);
		helper.setTo(mailTo);
		helper.setFrom(mailFrom);
		
		MimeMultipart multipart = new MimeMultipart("related");
		 
		// first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
        messageBodyPart.setContent(htmlText, "text/html");
        // add it
        multipart.addBodyPart(messageBodyPart);

        // second part (the image)
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource("C:\\ibomcha\\tempupload\\1.png");

        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");

        // add image to the multipart
        multipart.addBodyPart(messageBodyPart);

        // put everything together
        message.setContent(multipart);
        

		mailSender.send(message);
	}
	

}
