package br.com.smartclinmed.web.services.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.smartclinmed.web.domain.acessos.Usuario;
import br.com.smartclinmed.web.domain.atendimento.Agendamento;

public interface EmailService {

	void sendOrderConfirmationEmail(Agendamento obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Agendamento obj);

	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);
}
