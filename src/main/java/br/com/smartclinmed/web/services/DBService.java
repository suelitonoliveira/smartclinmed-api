package br.com.smartclinmed.web.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.repositories.PacienteRepository;

@Service
public class DBService {
	@Autowired
	private PacienteRepository pacienteRepository;

	public void instantiateTestDatabase() throws ParseException {

		Paciente paciente = new Paciente(null, "Maria", "nomeSocial", "rg", "cpf", "email", LocalDate.now(), 1, 1, 1,
			"idade", "nomeTitular", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), null);
		
		Paciente paciente1 = new Paciente(null, "Maria", "nomeSocial", "rg", "cpf", "email", LocalDate.now(), 1, 1, 1,
				"idade", "nomeTitular", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), null);
		
		pacienteRepository.saveAll(Arrays.asList(paciente,paciente1));

	}

}
