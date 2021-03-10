package br.com.smartclinmed.web.dto.software;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.smartclinmed.web.enums.TipoContratacaoInquilino;

import br.com.smartclinmed.web.enums.TipoStatusComum;

import br.com.smartclinmed.web.enums.TipoCliente;

public class InquilinoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fantasia;
	private String razaoSocial;
	private TipoCliente tipoCliente;
	private TipoStatusComum statusComum;
	private TipoContratacaoInquilino tipoContratacao;
	private String nRegistro;
	private String imagem;
	private String imagem64;

	private Set<String> telefones = new HashSet<>();

	public InquilinoDTO() {

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
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public TipoStatusComum getStatusComum() {
		return statusComum;
	}

	public void setStatusComum(TipoStatusComum statusComum) {
		this.statusComum = statusComum;
	}

	public TipoContratacaoInquilino getTipoContratacao() {
		return tipoContratacao;
	}

	public void setTipoContratacao(TipoContratacaoInquilino tipoContratacao) {
		this.tipoContratacao = tipoContratacao;
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

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

}
