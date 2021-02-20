package br.com.smartclinmed.web.dto.acessos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.smartclinmed.web.acessos.UsuarioPerfil;
import br.com.smartclinmed.web.enums.TipoStatusComum;

//@UsuarioUpdate
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String senha;
	private TipoStatusComum statusComum;
	private List<UsuarioPerfil> perfis = new ArrayList<>();

	public UsuarioDTO() {

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
