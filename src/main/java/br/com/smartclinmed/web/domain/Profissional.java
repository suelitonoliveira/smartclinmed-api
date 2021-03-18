package br.com.smartclinmed.web.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


import br.com.smartclinmed.web.domain.software.Inquilino;

@Entity
public class Profissional implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "inquilino_id")
	private Inquilino inquilino;
	
	private String nome;
	private Integer sexo;
	private String cpf;
	private String rg;
	private String idade;
	private LocalDate dataNascimento;
	private String email;
	private Integer tipoProfissional;
	private String numeroConsenho;
	private String assinatura;
	private Integer statusComum;
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "indicacao_id")
	private Indicacao indicacao;

	private LocalDateTime dtInclusao;
	private LocalDateTime dtAlteracao;

	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@ElementCollection
	@CollectionTable(name = "TELEFONE_Profissional")
	private Set<String> telefones = new HashSet<>();


	public Profissional() {
	}

	public Profissional(Long id, Inquilino inquilino, String nome, Integer sexo, String cpf, String rg, String idade,
			LocalDate dataNascimento, String email, Integer tipoProfissional, String numeroConsenho, String assinatura,
			Integer statusComum, Integer status, Indicacao indicacao, LocalDateTime dtInclusao,
			LocalDateTime dtAlteracao, Endereco endereco, Set<String> telefones) {
		super();
		this.id = id;
		this.inquilino = inquilino;
		this.nome = nome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.rg = rg;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.tipoProfissional = tipoProfissional;
		this.numeroConsenho = numeroConsenho;
		this.assinatura = assinatura;
		this.statusComum = statusComum;
		this.status = status;
		this.indicacao = indicacao;
		this.dtInclusao = dtInclusao;
		this.dtAlteracao = dtAlteracao;
		this.endereco = endereco;
		this.telefones = telefones;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTipoProfissional() {
		return tipoProfissional;
	}

	public void setTipoProfissional(Integer tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}

	public String getNumeroConsenho() {
		return numeroConsenho;
	}

	public void setNumeroConsenho(String numeroConsenho) {
		this.numeroConsenho = numeroConsenho;
	}

	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}

	public Integer getStatusComum() {
		return statusComum;
	}

	public void setStatusComum(Integer statusComum) {
		this.statusComum = statusComum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Indicacao getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(Indicacao indicacao) {
		this.indicacao = indicacao;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
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
		Profissional other = (Profissional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
