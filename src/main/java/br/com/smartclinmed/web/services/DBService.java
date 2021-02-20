package br.com.smartclinmed.web.services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.acessos.Permissao;
import br.com.smartclinmed.web.acessos.Usuario;
import br.com.smartclinmed.web.acessos.UsuarioPerfil;
import br.com.smartclinmed.web.domain.Agendamento;
import br.com.smartclinmed.web.domain.Cidade;
import br.com.smartclinmed.web.domain.Endereco;
import br.com.smartclinmed.web.domain.Estado;
import br.com.smartclinmed.web.domain.Indicacao;
import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.domain.Pais;
import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.enums.Perfil;
import br.com.smartclinmed.web.enums.TipoAgendamento;
import br.com.smartclinmed.web.enums.TipoCliente;
import br.com.smartclinmed.web.enums.TipoContratacaoInquilino;
import br.com.smartclinmed.web.enums.TipoPaciente;
import br.com.smartclinmed.web.enums.TipoSexo;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.repositories.AgendamentoRepository;
import br.com.smartclinmed.web.repositories.CidadeRepository;
import br.com.smartclinmed.web.repositories.EnderecoRepository;
import br.com.smartclinmed.web.repositories.EstadoRepository;
import br.com.smartclinmed.web.repositories.IndicacaoRepository;
import br.com.smartclinmed.web.repositories.PacienteRepository;
import br.com.smartclinmed.web.repositories.PaisRepository;
import br.com.smartclinmed.web.repositories.acessos.PermissoesRepository;
import br.com.smartclinmed.web.repositories.acessos.UsuarioPerfilRepository;
import br.com.smartclinmed.web.repositories.acessos.UsuarioRepository;
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
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private PermissoesRepository permissoesRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {

		Permissao perm1 = new Permissao(null, "ROLE_Usuario_List");
		Permissao perm2 = new Permissao(null, "ROLE_Usuario_Insert");
		Permissao perm3 = new Permissao(null, "ROLE_Usuario_Update");
		Permissao perm4 = new Permissao(null, "ROLE_Usuario_Delete");

		permissoesRepository.saveAll(Arrays.asList(perm1, perm2, perm3, perm4));

		Inquilino inq1 = new Inquilino(null, "SMARTCLINMED", "SMARTICLINMED - SISTEMA INTELIGENTE PARA CLINICAS",
				TipoCliente.PESSOA_JURIDICA, TipoStatusComum.ATIVO, TipoContratacaoInquilino.FULL, "24861750000116",
				"imagem", "imagem64", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), null);
		inquilinoRepository.save(inq1);

		Usuario user1 = new Usuario(null, "sueliton", inq1, "suelitondeoliveira@gmail.com", TipoStatusComum.ATIVO,
				pe.encode("102030"), LocalDateTime.now(), null);
		user1.addPerfil(Perfil.ADMIN);
		
		Usuario user2 = new Usuario(null, "Usuario", inq1, "sueliton.oliveira@htomail.com", TipoStatusComum.ATIVO,
				pe.encode("102030"), LocalDateTime.now(), null);
		user2.addPerfil(Perfil.USUARIO);
		
		UsuarioPerfil perf1 = new UsuarioPerfil(null, "PROPRIETÁRIO", LocalDateTime.now(), null);
		UsuarioPerfil perf2 = new UsuarioPerfil(null, "GESTOR", LocalDateTime.now(), null);
		perf1.addPermissao(perm1);
		perf1.addPermissao(perm2);
		perf1.addPermissao(perm3);
		perf1.addPermissao(perm4);
				
		usuarioPerfilRepository.saveAll(Arrays.asList(perf1, perf2));
		/*
		 * user1.addPerfil(perf1); user1.addPerfil(perf2);
		 */
	
		usuarioRepository.saveAll(Arrays.asList(user1, user2));

		Pais p1 = new Pais(null, "Brasil", "BRA");
		paisRepository.save(p1);

		Estado est1 = new Estado(null, "Goias", "GO", p1);
		estadoRepository.save(est1);

		Cidade cid1 = new Cidade(null, "Goiânia", "GYN", 62, est1);
		cidadeRepository.save(cid1);
		Indicacao ind1 = new Indicacao(null, inq1, "Google");
		Indicacao ind2 = new Indicacao(null, inq1, "Site");
		indicacaoRepository.saveAll(Arrays.asList(ind1, ind2));

		Endereco e1 = new Endereco(null, inq1, "cep", "logradouro", "numero", "complemento", "bairro", cid1);
		Endereco e2 = new Endereco(null, inq1, "cep", "logradouro", "numero", "complemento", "bairro", cid1);

		Paciente pac1 = new Paciente(null, inq1, "Maria", "nomeSocial", "rg", "cpf", "suelitondeoliveira@gmail.com",
				null, TipoSexo.FEMININO, TipoPaciente.TITULAR, TipoStatusComum.ATIVO, "idade", "nomeTitular", ind1, e2,
				LocalDateTime.now(), null);
		pac1.getTelefones().addAll(Arrays.asList("61992532326", "61992532327"));

		Paciente pac2 = new Paciente(null, inq1, "Jose", "Jade", "rg", "cpf", "suelitondeoliveira@gmail.com", null,
				TipoSexo.MASCULINO, TipoPaciente.TITULAR, TipoStatusComum.ATIVO, "idade", "nomeTitular", ind1, e1,
				LocalDateTime.now(), null);
		pac2.getTelefones().addAll(Arrays.asList("61992532326", "61992532327"));

		e1.getPacientes().add(pac1);
		e2.getPacientes().add(pac2);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pacienteRepository.saveAll(Arrays.asList(pac1, pac2));

		Agendamento ag1 = new Agendamento(null, inq1, null, null, pac1, TipoAgendamento.CONSULTA, LocalDateTime.now(),
				null);
		Agendamento ag2 = new Agendamento(null, inq1, null, null, pac2, TipoAgendamento.EXAME, LocalDateTime.now(),
				null);
		agendamentoRepository.saveAll(Arrays.asList(ag1, ag2));

	}

}
