package br.com.smartclinmed.web.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.enums.TipoProfissional;
import br.com.smartclinmed.web.enums.TipoSexo;
import br.com.smartclinmed.web.enums.TipoStatusComum;

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
	private LocalDate dataNascimento;
	private String email;
	private Integer tipoProfissional;
	private String numeroConsenho;
	private String assinatura;
	private Integer statusComum;
	
	@JsonManagedReference
	@ManyToMany(mappedBy = "profissional")
	private List<Especialidade> especialidade = new ArrayList<>();

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

	public Profissional(Long id, Inquilino inquilino, String nome, TipoSexo sexo, String cpf, String rg,
			LocalDate dataNascimento, String email, TipoProfissional tipoProfissional, String numeroConsenho,
			String assinatura, TipoStatusComum statusComum, Indicacao indicacao, LocalDateTime dtInclusao,
			LocalDateTime dtAlteracao, Endereco endereco) {
		super();
		this.id = id;
		this.inquilino = inquilino;
		this.nome = nome;
		this.sexo = (sexo == null) ? 1 : sexo.getCod();
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.tipoProfissional = (tipoProfissional == null) ? 1 : tipoProfissional.getCod();
		this.numeroConsenho = numeroConsenho;
		this.assinatura = assinatura;
		this.statusComum = (statusComum == null) ? 1 : statusComum.getCod();
		this.indicacao = indicacao;
		this.dtInclusao = dtInclusao;
		this.dtAlteracao = dtAlteracao;
		this.endereco = endereco;

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

	public TipoSexo getSexo() {
		return TipoSexo.toEnum(sexo);
	}

	public void setSexo(TipoSexo sexo) {
		this.sexo = sexo.getCod();
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

	public TipoProfissional getTipoProfissional() {
		return TipoProfissional.toEnum(tipoProfissional);
	}

	public void setTipoProfissional(TipoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional.getCod();
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

	public TipoStatusComum getStatusComum() {
		return TipoStatusComum.toEnum(statusComum);
	}

	public void setStatusComum(TipoStatusComum statusComum) {
		this.statusComum = statusComum.getCod();
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

	public List<Especialidade> getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(List<Especialidade> especialidade) {
		this.especialidade = especialidade;
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
