package br.com.smartclinmed.web.services.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import br.com.smartclinmed.web.domain.atendimento.Agendamento;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {

		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado!");
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Agendamento obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando envio de HTML...");
		LOG.info(msg.toString());
		LOG.info("Email enviado!");
		
	}

}
