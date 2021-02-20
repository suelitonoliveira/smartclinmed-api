package br.com.smartclinmed.web.dto.acessos;

import java.io.Serializable;

//@UsuarioUpdateProfile
public class UsuarioPerfilDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String senha;

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

}
