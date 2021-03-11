package br.com.smartclinmed.web.services.email;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.smartclinmed.web.acessos.Usuario;
import br.com.smartclinmed.web.domain.Agendamento;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private  TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Agendamento obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromAgendamento(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromAgendamento(Agendamento obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getPaciente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Agendamento efetuado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplateAgendamento(Agendamento obj) {
		Context context = new Context();
		context.setVariable("agendamento", obj);
		return templateEngine.process("email/confirmacaoAgendamento", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Agendamento obj) {
		
		try {
			MimeMessage mm = prepareMimeMessageFromAgendamento(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromAgendamento(Agendamento obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getPaciente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Agendamento Efetudo! Código: " +obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateAgendamento(obj), true);
		
		return mimeMessage;
	} 
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("SMARTCLIN - Nova solicitação de senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
}
