package com.healthcare.service;

import com.healthcare.model.EmailDetails;

public interface EmailService {


	String sendSimpleMail(EmailDetails details);


	String sendMailWithAttachment(EmailDetails details);
}
