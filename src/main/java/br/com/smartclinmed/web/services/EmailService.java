package br.com.smartclinmed.web.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.smartclinmed.web.domain.Agendamento;

public interface EmailService {

	void sendOrderConfirmationEmail(Agendamento obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Agendamento obj);

	void sendHtmlEmail(MimeMessage msg);
}
