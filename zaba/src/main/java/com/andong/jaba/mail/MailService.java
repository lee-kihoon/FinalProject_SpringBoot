package com.andong.jaba.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(MailTo mail) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(mail.getAddress());
//        message.setFrom(mail.getAddress());// from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
//        message.setSubject(mail.getTitle());
//        message.setText(mail.getMessage());
//        
//        mailSender.send(message);
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
//			메일 설정
			helper.setTo(mail.getAddress());
			helper.setSubject(mail.getTitle());
			helper.setText(mail.getMessage() + "\n\n From. " + mail.getFromAddress());
//			메일 전송 수행
			mailSender.send(message);

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

}
