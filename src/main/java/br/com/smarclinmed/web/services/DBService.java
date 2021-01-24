package br.com.smarclinmed.web.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.smarclinmed.web.domain.Paciente;
import br.com.smarclinmed.web.repositories.PacienteRepository;

@Service
public class DBService {
	@Autowired
	private PacienteRepository pacienteRepository;

	public void instantiateTestDatabase() throws ParseException {

		Paciente paciente = new Paciente(null, "Maria", "nomeSocial", "rg", "cpf", "email", LocalDate.now(), 1, 1, 1,
			"idade", "nomeTitular", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), null);
		
		pacienteRepository.save(paciente);

	}

}
