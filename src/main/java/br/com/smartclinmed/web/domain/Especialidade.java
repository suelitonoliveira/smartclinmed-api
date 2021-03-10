package br.com.smartclinmed.web.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Especialidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cbosTiss2;
	private String cbosTiss3;
	
	public Especialidade() {
	}

	public Especialidade(long id, String nome, String cbosTiss2, String cbosTiss3) {
		super();
		this.id = id;
		this.nome = nome;
		this.cbosTiss2 = cbosTiss2;
		this.cbosTiss3 = cbosTiss3;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCbosTiss2() {
		return cbosTiss2;
	}

	public void setCbosTiss2(String cbosTiss2) {
		this.cbosTiss2 = cbosTiss2;
	}

	public String getCbosTiss3() {
		return cbosTiss3;
	}

	public void setCbosTiss3(String cbosTiss3) {
		this.cbosTiss3 = cbosTiss3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Especialidade other = (Especialidade) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
