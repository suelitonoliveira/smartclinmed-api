package br.com.smartclinmed.web.dto.software;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.smartclinmed.web.enums.TipoCliente;
import br.com.smartclinmed.web.enums.TipoContratacaoInquilino;
import br.com.smartclinmed.web.services.validation.software.InquilinoInsert;

@InquilinoInsert
public class InquilinoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max = 200, message = "O tamanho deve ser entre 5 e 200 caracteres  ")
	private String fantasia;
	private String razaoSocial;
	private TipoCliente tipoCliente;
	private TipoContratacaoInquilino tipoContratacao;
	private String nRegistro;
	private String imagem;
	private String imagem64;
	@Email(message = "Email inválido!")
	@NotEmpty(message = "Preenchimento obrigatório")
	private String email;

	private Set<String> telefones = new HashSet<>();

	public InquilinoNewDTO() {

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
