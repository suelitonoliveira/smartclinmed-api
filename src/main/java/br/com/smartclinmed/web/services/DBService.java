package br.com.smartclinmed.web.services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.Cidade;
import br.com.smartclinmed.web.domain.Endereco;
import br.com.smartclinmed.web.domain.Estado;
import br.com.smartclinmed.web.domain.Indicacao;
import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.domain.Pais;
import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.enums.TipoCliente;
import br.com.smartclinmed.web.enums.TipoContratacaoInquilino;
import br.com.smartclinmed.web.enums.TipoPaciente;
import br.com.smartclinmed.web.enums.TipoSexo;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.repositories.CidadeRepository;
import br.com.smartclinmed.web.repositories.EnderecoRepository;
import br.com.smartclinmed.web.repositories.EstadoRepository;
import br.com.smartclinmed.web.repositories.IndicacaoRepository;
import br.com.smartclinmed.web.repositories.PacienteRepository;
import br.com.smartclinmed.web.repositories.PaisRepository;
import br.com.smartclinmed.web.repositories.software.InquilinoRepository;

@Service
public class DBService {
	@Autowired
	private InquilinoRepository inquilinoRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private IndicacaoRepository indicacaoRepository;
	@Autowired
	private PaisRepository paisRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public void instantiateTestDatabase() throws ParseException {

		Inquilino inq1 = new Inquilino(null, "fantasia", "razaoSocial", TipoCliente.PESSOA_JURIDICA,
				TipoStatusComum.ATIVO, TipoContratacaoInquilino.FULL, "nRegistro", "imagem", "imagem64",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), null);
		inquilinoRepository.save(inq1);

		Pais p1 = new Pais(null, "Brasil", "BRA");
		paisRepository.save(p1);

		Estado est1 = new Estado(null, "Goias", "GO", p1);
		estadoRepository.save(est1);

		Cidade cid1 = new Cidade(null, "Goiânia", "GYN", 62, est1);
		cidadeRepository.save(cid1);
		Indicacao ind1 = new Indicacao(null, inq1, "Google");
		indicacaoRepository.save(ind1);
		

		
		
		
		Endereco e1 = new Endereco(null, inq1, "cep", "logradouro", "numero", "complemento", "bairro", cid1);
		Endereco e2 = new Endereco(null, inq1, "cep", "logradouro", "numero", "complemento", "bairro", cid1);
		
		Paciente pac1 = new Paciente(null, inq1, "Maria", "nomeSocial", "rg", "cpf", "teste@email", null,
				TipoSexo.FEMININO, TipoPaciente.TITULAR, TipoStatusComum.ATIVO, "idade", "nomeTitular", ind1,e2,
				LocalDateTime.now(), null);
		pac1.getTelefones().addAll(Arrays.asList("61992532326", "61992532327"));
		
		Paciente pac2 = new Paciente(null, inq1, "Jose", "Jade", "rg", "cpf", "teste@email", null, TipoSexo.MASCULINO,
				TipoPaciente.TITULAR, TipoStatusComum.ATIVO, "idade", "nomeTitular", ind1,e1, LocalDateTime.now(), null);
		pac2.getTelefones().addAll(Arrays.asList("61992532326", "61992532327"));
	
		e1.getPacientes().add(pac1);
		e2.getPacientes().add(pac2);
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		pacienteRepository.saveAll(Arrays.asList(pac1, pac2));
	

	}

}
