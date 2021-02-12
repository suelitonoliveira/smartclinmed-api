package br.com.smartclinmed.web.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.smartclinmed.web.services.DBService;
import br.com.smartclinmed.web.services.EmailService;
import br.com.smartclinmed.web.services.MockEmailService;


@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbservice;

	@Bean
	public boolean instatiateDatabase() throws ParseException {
		dbservice.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
