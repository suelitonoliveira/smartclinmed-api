package br.com.smartclinmed.web.dto.acessos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;

import br.com.smartclinmed.web.acessos.UsuarioPerfil;

public class UsuarioNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	@Email
	private String email;
	private String senha;
	private String imagem;
	private String imagem64;
	private List<UsuarioPerfil> perfis = new ArrayList<>();

	public UsuarioNewDTO() {

	}

	public UsuarioNewDTO(String nome, @Email String email, String senha, List<UsuarioPerfil> perfis) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfis = perfis;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public List<UsuarioPerfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<UsuarioPerfil> perfis) {
		this.perfis = perfis;
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

}
