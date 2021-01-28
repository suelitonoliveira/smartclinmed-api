package br.com.smartclinmed.web.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.enums.TipoContratacaoInquilino;
import br.com.smartclinmed.web.enums.TipoCliente;
import br.com.smartclinmed.web.enums.TipoPaciente;
import br.com.smartclinmed.web.enums.TipoSexo;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.repositories.PacienteRepository;
import br.com.smartclinmed.web.repositories.software.InquilinoRepository;

@Service
public class DBService {
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private InquilinoRepository inquilinoRepository;

	public void instantiateTestDatabase() throws ParseException {

		Inquilino inq1 = new Inquilino(null, "fantasia", "razaoSocial", TipoCliente.PESSOA_JURIDICA,
				TipoStatusComum.ATIVO, TipoContratacaoInquilino.FULL, "nRegistro", "imagem", "imagem64",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), null);
		inquilinoRepository.save(inq1);

		Paciente paciente1 = new Paciente(null, inq1,"Maria", "nomeSocial", "rg", "cpf", "email", LocalDate.now(),
				TipoSexo.MASCULINO, TipoPaciente.TITULAR, TipoStatusComum.ATIVO, "idade", "nomeTitular",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), null);

		Paciente paciente2 = new Paciente(null, inq1,"Maria", "nomeSocial", "rg", "cpf", "email", LocalDate.now(),
				TipoSexo.FEMININO, TipoPaciente.DEPENDENTE, TipoStatusComum.ATIVO, "idade", "nomeTitular",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), null);

		pacienteRepository.saveAll(Arrays.asList(paciente1, paciente2));

	}

}
