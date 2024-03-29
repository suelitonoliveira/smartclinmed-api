package br.com.smartclinmed.web.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.Cidade;
import br.com.smartclinmed.web.domain.Endereco;
import br.com.smartclinmed.web.domain.Especialidade;
import br.com.smartclinmed.web.domain.Estado;
import br.com.smartclinmed.web.domain.Indicacao;
import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.domain.Pais;
import br.com.smartclinmed.web.domain.Profissional;
import br.com.smartclinmed.web.domain.acessos.Permissao;
import br.com.smartclinmed.web.domain.acessos.Usuario;
import br.com.smartclinmed.web.domain.acessos.UsuarioPerfil;
import br.com.smartclinmed.web.domain.atendimento.Agendamento;
import br.com.smartclinmed.web.domain.atendimento.Triagem;
import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.enums.TipoAgendamento;
import br.com.smartclinmed.web.enums.TipoCliente;
import br.com.smartclinmed.web.enums.TipoContratacaoInquilino;
import br.com.smartclinmed.web.enums.TipoPaciente;
import br.com.smartclinmed.web.enums.TipoProfissional;
import br.com.smartclinmed.web.enums.TipoSexo;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.repositories.CidadeRepository;
import br.com.smartclinmed.web.repositories.EnderecoRepository;
import br.com.smartclinmed.web.repositories.EspecialidadeRepository;
import br.com.smartclinmed.web.repositories.EstadoRepository;
import br.com.smartclinmed.web.repositories.IndicacaoRepository;
import br.com.smartclinmed.web.repositories.PacienteRepository;
import br.com.smartclinmed.web.repositories.PaisRepository;
import br.com.smartclinmed.web.repositories.ProfissionalRepository;
import br.com.smartclinmed.web.repositories.acessos.PermissoesRepository;
import br.com.smartclinmed.web.repositories.acessos.UsuarioPerfilRepository;
import br.com.smartclinmed.web.repositories.acessos.UsuarioRepository;
import br.com.smartclinmed.web.repositories.atendimento.AgendamentoRepository;
import br.com.smartclinmed.web.repositories.atendimento.TriagemRepository;
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
	private EspecialidadeRepository especialidadeRepository;
	@Autowired
	private TriagemRepository triagemRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {

		Permissao perm1 = new Permissao(null, "ROLE_Usuario_List");
		Permissao perm2 = new Permissao(null, "ROLE_Usuario_Insert");
		Permissao perm3 = new Permissao(null, "ROLE_Usuario_Update");
		Permissao perm4 = new Permissao(null, "ROLE_Usuario_Delete");
		Permissao perm5 = new Permissao(null, "ROLE_Especialidade_List");
		Permissao perm6 = new Permissao(null, "ROLE_Triagem_List");
		Permissao perm7 = new Permissao(null, "ROLE_Triagem_Insert");
		Permissao perm8 = new Permissao(null, "ROLE_Triagem_Update");
		Permissao perm9 = new Permissao(null, "ROLE_Inquilino_List");
		Permissao perm10 = new Permissao(null, "ROLE_Inquilino_Insert");
		Permissao perm11 = new Permissao(null, "ROLE_Inquilino_Update");
		Permissao perm12 = new Permissao(null, "ROLE_Inquilino_Delete");
		Permissao perm13 = new Permissao(null, "ROLE_Paciente_List");
		Permissao perm14 = new Permissao(null, "ROLE_Paciente_Insert");
		Permissao perm15 = new Permissao(null, "ROLE_Paciente_Update");
		Permissao perm16 = new Permissao(null, "ROLE_Paciente_Delete");
		Permissao perm17 = new Permissao(null, "ROLE_Especialidad_List");
		Permissao perm18 = new Permissao(null, "ROLE_Especialidade_Insert");
		Permissao perm19 = new Permissao(null, "ROLE_Especialidade_Update");
		Permissao perm20 = new Permissao(null, "ROLE_Especialidade_Delete");
		Permissao perm21 = new Permissao(null, "ROLE_Profissional_List");
		Permissao perm22 = new Permissao(null, "ROLE_Profissional_Insert");
		Permissao perm23 = new Permissao(null, "ROLE_Profissional_Update");
		Permissao perm24 = new Permissao(null, "ROLE_Profissional_Delete");

		permissoesRepository
				.saveAll(Arrays.asList(perm1, perm2, perm3, perm4, perm5, perm6, perm7, perm8, perm9, perm10, perm11,
						perm12, perm13, perm14, perm15, perm16, perm17, perm18, perm19, perm20, perm21, perm22, perm23, perm24));

		Inquilino inq1 = new Inquilino(null, "SMARTCLINMED", "SMARTICLINMED - SISTEMA INTELIGENTE PARA CLINICAS",
				TipoCliente.PESSOA_JURIDICA, TipoStatusComum.ATIVO, TipoContratacaoInquilino.FULL, "24861750000116",
				"imagem", "imagem64", "teste@email", pe.encode("1234"), LocalDateTime.now(), null);
		inq1.getTelefones().addAll(Arrays.asList("61992532326", "61992532327"));

		Inquilino inq2 = new Inquilino(null, "TESTE", "SISTEMA INTELIGENTE PARA CLINICAS", TipoCliente.PESSOA_JURIDICA,
				TipoStatusComum.ATIVO, TipoContratacaoInquilino.FULL, "95434958000105", "imagem", "imagem64",
				"suelitondeoliveira@gmail.com", pe.encode("1234"), LocalDateTime.now(), null);
		inq1.getTelefones().addAll(Arrays.asList("6199999999", "61992532215"));

		inquilinoRepository.saveAll(Arrays.asList(inq1, inq2));

		Usuario user1 = new Usuario(null, inq1, "Sueliton", "suelitondeoliveira@gmail.com", TipoStatusComum.ATIVO,
				pe.encode("102030"), "imagem", "imagem64", LocalDateTime.now(), null);

		Usuario user2 = new Usuario(null, inq1, "Usuario", "sueliton.oliveira@htomail.com", TipoStatusComum.ATIVO,
				pe.encode("102030"), "imagem", "imagem64", LocalDateTime.now(), null);

		UsuarioPerfil perf1 = new UsuarioPerfil(null, "PROPRIETÁRIO", LocalDateTime.now(), null);
		UsuarioPerfil perf2 = new UsuarioPerfil(null, "GESTOR", LocalDateTime.now(), null);
		perf1.addPermissao(perm1);
		perf1.addPermissao(perm2);
		perf1.addPermissao(perm3);
		perf1.addPermissao(perm4);
		perf1.addPermissao(perm5);
		perf1.addPermissao(perm6);
		perf1.addPermissao(perm7);
		perf1.addPermissao(perm8);
		perf1.addPermissao(perm9);
		perf1.addPermissao(perm10);
		perf1.addPermissao(perm11);
		perf1.addPermissao(perm12);
		perf1.addPermissao(perm13);
		perf1.addPermissao(perm14);
		perf1.addPermissao(perm15);
		perf1.addPermissao(perm16);
		perf1.addPermissao(perm17);
		perf1.addPermissao(perm18);
		perf1.addPermissao(perm19);
		perf1.addPermissao(perm20);
		perf1.addPermissao(perm21);
		perf1.addPermissao(perm22);
		perf1.addPermissao(perm23);
		perf1.addPermissao(perm24);
		
		usuarioPerfilRepository.saveAll(Arrays.asList(perf1, perf2));
		user1.addPerfil(perf1);
		user1.addPerfil(perf2);
		user2.addPerfil(perf2);

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
				LocalDate.of(1986, 8, 27), TipoSexo.FEMININO, TipoPaciente.TITULAR, TipoStatusComum.ATIVO, 10, "nomeTitular", ind1, e2,
				LocalDateTime.now(), null);
		pac1.getTelefones().addAll(Arrays.asList("61992532326", "61992532327"));

		Paciente pac2 = new Paciente(null, inq1, "Jose", "Jade", "rg", "cpf", "suelitondeoliveira@gmail.com", LocalDate.of(2020, 2, 23),
				TipoSexo.MASCULINO, TipoPaciente.TITULAR, TipoStatusComum.ATIVO, 10, "nomeTitular", ind1, e1,
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

		Profissional prof1 = new Profissional(null, inq1, "nome", TipoSexo.FEMININO, "cpf", "rg", LocalDate.now(),
				"email", TipoProfissional.MEDICO, "numeroConsenho", "assinatura", TipoStatusComum.ATIVO, ind1,
				LocalDateTime.now(), null, e1);
		prof1.getTelefones().addAll(Arrays.asList("61992532326", "61992532327"));

		Especialidade esp1 = new Especialidade(null, inq1, "Neurologia", "225112", "");
		Especialidade esp2 = new Especialidade(null, inq1, "Cardiologia", "225120", "");
		Especialidade esp3 = new Especialidade(null, inq1, "Psiquiatria", "225133", "");
		
		esp1.getProfissionais().addAll(Arrays.asList(prof1));
		esp2.getProfissionais().addAll(Arrays.asList(prof1));
		
		prof1.getEspecialidades().addAll(Arrays.asList(esp1, esp2));
		
		profissionalRepository.save(prof1);
		especialidadeRepository.saveAll(Arrays.asList(esp1, esp2, esp3));

		Triagem triagem = new Triagem(null, inq1, 87.0, 1.75, 12.8, 37.0, 26.5);
		triagemRepository.save(triagem);

	}

}
