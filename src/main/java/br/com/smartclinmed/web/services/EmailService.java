package br.com.smartclinmed.web.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.smartclinmed.web.domain.Agendamento;

public interface EmailService {

	void sendOrderConfirmationEmail(Agendamento obj);

	void sendEmail(SimpleMailMessage msg);
}
