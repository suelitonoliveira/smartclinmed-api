package br.com.smartclinmed.web.dto.acessos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class UsuarioPermissoesDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Mandatory")
	private Integer idUsuario;
	@NotEmpty(message = "Mandatory")
	private Integer idPerfil;

	public UsuarioPermissoesDTO() {

	}

	public UsuarioPermissoesDTO(Integer idUsuario, Integer idPerfil) {
		super();
		this.idUsuario = idUsuario;
		this.idPerfil = idPerfil;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

}
