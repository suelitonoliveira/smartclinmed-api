package br.com.smartclinmed.web.dto;

import java.io.Serializable;

import br.com.smartclinmed.web.domain.Paciente;

public class PacienteDTOFind implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String nomeSocial;

	public PacienteDTOFind() {

	}

	public PacienteDTOFind(Paciente obj) {
		id = obj.getId();
		nome = obj.getNome();
		nomeSocial = obj.getNomeSocial();
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

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

}
