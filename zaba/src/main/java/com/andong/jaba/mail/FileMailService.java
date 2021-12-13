package com.andong.jaba.mail;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class FileMailService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(MailTo mail) {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setTo(mail.getAddress()); // 받는사람 이메일
			messageHelper.setFrom(mail.getFromAddress()); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setSubject(mail.getTitle()); // 메일제목은 생략이 가능하다
			messageHelper.setText(mail.getMessage() + "\n\nfrom.\n"+mail.getFromAddress()); // 메일 내용

			// 파일첨부
			System.out.println(mail.getFileName());
			System.out.println(mail.getFilePath());
			FileSystemResource fsr = new FileSystemResource(mail.getFilePath());
			messageHelper.addAttachment(mail.getFileName(), fsr);

			mailSender.send(message);
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
