package br.com.smartclinmed.web.dto.acessos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.smartclinmed.web.acessos.UsuarioPerfil;
import br.com.smartclinmed.web.enums.TipoStatusComum;

//@UsuarioUpdate
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String senha;
	private String email;
	private TipoStatusComum statusComum;
	private List<UsuarioPerfil> perfis = new ArrayList<>();

	public UsuarioDTO() {

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

	public TipoStatusComum getStatusComum() {
		return statusComum;
	}

	public void setStatusComum(TipoStatusComum statusComum) {
		this.statusComum = statusComum;
	}

	public List<UsuarioPerfil> getPerfis() {
		return this.perfis;
	}

	public void setPerfis(List<UsuarioPerfil> perfis) {
		this.perfis = perfis;
	}

}
