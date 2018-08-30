package org.cn.kkl.erp.util;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
	
	private JavaMailSender javaMailSender;
	private String from;

	public void setFrom(String from) {
		this.from = from;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	/**
	 * send mail 
	 * @param to :email receiver
	 * @param subject:email subject
	 * @param text : email content
	 */
	public void sendMail(String to,String subject,String content) throws MessagingException{
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		messageHelper.setFrom(from);
		messageHelper.setTo(to);
		messageHelper.setSentDate(new Date());
		messageHelper.setSubject(subject);
		messageHelper.setText(content);
		javaMailSender.send(mimeMessage);
	}
	
}
