package com.oinam.tutorial.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oinam.tutorial.spring.service.ApplicationMailer;
import com.oinam.tutorial.spring.service.MailService;

@Controller
public class FileUploadController {

	@Autowired
	MailService mailService;

	@Autowired
	ApplicationMailer applicationMailer;

	// @Autowired
	// JavaMailSender mailSender;

	@GetMapping("/")
	public String fileUploadForm(Model model) {
		return "fileUploadForm";
	}

	// Handling file upload request
	@PostMapping("/fileUpload")
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {

		// Save file on system
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new File("C:/ibomcha/tempupload", file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();

			String mailBody = "This is just a test message";
			// Mail it after file upload
			//applicationMailer.sendMail("oinam.thopper.singh@gmail.com", "ibomg@yahoo.com", "Testing with subject",
			//		mailBody);
			//mailService.sendEmail("ibomg@yahoo.com", "Subject: File uploaded successfully", "Body: file uploaded successfully");
			try {
				mailService.sendSimpleMessage("oinam.thopper.singh@gmail.com","ibomg@yahoo.com", "Subject: File uploaded successfully", "Body: file uploaded successfully");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Message Send...");
		} else {
			return new ResponseEntity<>("Invalid file.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("File Uploaded Successfully.", HttpStatus.OK);
	}

}
