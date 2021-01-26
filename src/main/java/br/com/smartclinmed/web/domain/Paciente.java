package br.com.smartclinmed.web.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.smartclinmed.web.enums.TipoPaciente;
import br.com.smartclinmed.web.enums.TipoSexo;
import br.com.smartclinmed.web.enums.TipoStatusComum;

@Entity
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String nomeSocial;
	private String rg;
	private String cpf;
	private String email;
	private LocalDate dataNascimento;
	private Integer sexo;
	private Integer tipoPaciente;
	private Integer statusComum;
	private String idade;
	private String nomeTitular;
	private LocalDateTime dtInclusao;
	private LocalDateTime dtAlteracao;

	public Paciente() {

	}

	public Paciente(Long id, String nome, String nomeSocial, String rg, String cpf, String email,
			LocalDate dataNascimento, TipoSexo sexo, TipoPaciente tipoPaciente, TipoStatusComum statusComum, String idade,
			String nomeTitular, LocalDateTime dtInclusao, LocalDateTime dtAlteracao) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeSocial = nomeSocial;
		this.rg = rg;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.sexo = (sexo == null)? 1: sexo.getCod();
		this.tipoPaciente = (tipoPaciente == null) ? 1: tipoPaciente.getCod();
		this.statusComum = (statusComum == null)? 1: statusComum.getCod();
		this.idade = idade;
		this.nomeTitular = nomeTitular;
		this.dtInclusao = dtInclusao;
		this.dtAlteracao = dtAlteracao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoSexo getSexo() {
		return TipoSexo.toEnum(sexo);
	}

	public void setSexo(TipoSexo sexo) {
		this.sexo = sexo.getCod();
	}

	public TipoPaciente getTipoPaciente() {
		return TipoPaciente.toEnum(tipoPaciente);
	}

	public void setTipoPaciente(TipoPaciente tipoPaciente) {
		this.tipoPaciente = tipoPaciente.getCod();
	}

	public TipoStatusComum getStatusComum() {
		return TipoStatusComum.toEnum(statusComum);
	}

	public void setStatusComum(TipoStatusComum statusComum) {
		this.statusComum = statusComum.getCod();
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public LocalDateTime getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(LocalDateTime dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public LocalDateTime getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(LocalDateTime dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
