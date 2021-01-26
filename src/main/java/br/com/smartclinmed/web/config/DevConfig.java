package br.com.smartclinmed.web.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.smartclinmed.web.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbservice;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instatiateDatabase() throws ParseException {
		if ("create".equals(strategy)) {
			dbservice.instantiateTestDatabase();
		}
		return true;
	}

	/*
	 * @Bean public EmailService emailService() { return new SmtpEmailService(); }
	 */

}