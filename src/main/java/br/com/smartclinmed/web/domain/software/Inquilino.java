package br.com.smartclinmed.web.domain.software;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.smartclinmed.web.enums.Perfil;
import br.com.smartclinmed.web.enums.TipoCliente;
import br.com.smartclinmed.web.enums.TipoContratacaoInquilino;
import br.com.smartclinmed.web.enums.TipoStatusComum;

@Entity
public class Inquilino implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fantasia;
	private String razaoSocial;
	private Integer tipoCliente;
	private Integer statusComum;
	private Integer tipoContratacao;
	private String nRegistro;
	private String imagem;
	private String imagem64;
	private String email;
	@JsonIgnore
	private String senha;
	private LocalDateTime dtInclusao;
	private LocalDateTime dtAlteracao;

	@ElementCollection
	@CollectionTable(name = "TELEFONE_INQUILINO")
	private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS_INQUILINO")
	private Set<Integer> perfis = new HashSet<>();

	public Inquilino() {
		addPerfil(Perfil.USUARIO);
	}

	public Inquilino(Long id, String fantasia, String razaoSocial, TipoCliente tipoCliente, TipoStatusComum statusComum,
			TipoContratacaoInquilino tipoContratacao, String nRegistro, String imagem, String imagem64, String email, String senha,
			LocalDateTime dtInclusao, LocalDateTime dtAlteracao) {
		super();
		this.id = id;
		this.fantasia = fantasia;
		this.razaoSocial = razaoSocial;
		this.tipoCliente = (tipoCliente == null) ? 1 : tipoCliente.getCod();
		this.statusComum = (statusComum == null) ? 1 : statusComum.getCod();
		this.tipoContratacao = (tipoContratacao == null) ? 8 : tipoContratacao.getCod(); // tipo de contratação básica
		this.nRegistro = nRegistro;
		this.imagem = imagem;
		this.imagem64 = imagem64;
		this.email = email;
		this.senha = senha;
		this.dtInclusao = dtInclusao;
		this.dtAlteracao = dtAlteracao;
		addPerfil(Perfil.USUARIO);

	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(tipoCliente);
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCod();
	}

	public TipoStatusComum getStatusComum() {
		return TipoStatusComum.toEnum(statusComum);
	}

	public void setStatusComum(TipoStatusComum statusComum) {
		this.statusComum = statusComum.getCod();
	}

	public TipoContratacaoInquilino getTipoContratacao() {
		return TipoContratacaoInquilino.toEnum(tipoContratacao);
	}

	public void setTipoContratacao(TipoContratacaoInquilino tipoContratacao) {
		this.tipoContratacao = tipoContratacao.getCod();
	}

	public String getnRegistro() {
		return nRegistro;
	}

	public void setnRegistro(String nRegistro) {
		this.nRegistro = nRegistro;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getImagem64() {
		return imagem64;
	}

	public void setImagem64(String imagem64) {
		this.imagem64 = imagem64;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
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
		Inquilino other = (Inquilino) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
