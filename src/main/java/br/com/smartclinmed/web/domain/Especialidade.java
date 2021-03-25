package br.com.smartclinmed.web.domain; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



import com.fasterxml.jackson.annotation.JsonIgnore;


import br.com.smartclinmed.web.domain.software.Inquilino;


@Entity
public class Especialidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "inquilino_id")
	private Inquilino inquilino;
	private String nome;
	private String cbosTiss2;
	private String cbosTiss3;
	

	@ManyToMany(mappedBy = "especialidades")
	private List<Profissional> profissionais = new ArrayList<>();
	
	public Especialidade() {
	}

	public Especialidade(Long id, Inquilino inquilino, String nome, String cbosTiss2, String cbosTiss3) {
		super();
		this.id = id;
		this.inquilino = inquilino;
		this.nome = nome;
		this.cbosTiss2 = cbosTiss2;
		this.cbosTiss3 = cbosTiss3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
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
	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissional(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}
	 
		@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
