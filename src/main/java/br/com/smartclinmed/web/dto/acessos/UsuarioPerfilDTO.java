package br.com.smartclinmed.web.dto.acessos;

import java.io.Serializable;

//@UsuarioUpdateProfile
public class UsuarioPerfilDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String senha;
	private String imagem;
	private String imagem64;

	public UsuarioPerfilDTO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
