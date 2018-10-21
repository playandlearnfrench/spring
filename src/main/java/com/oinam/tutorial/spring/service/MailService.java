package com.oinam.tutorial.spring.service;

import javax.mail.MessagingException;

public interface MailService {
	void sendEmail(String recipientAddress, String subject, String message);
	void sendEmail(String[] recipientAddress, String[] bcc, String[] cc, String subject, String message);
	void sendSimpleMessage(final String mailFrom, final String mailTo, final String subject,
			final String mailBody) throws MessagingException;
}
