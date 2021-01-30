package br.com.smartclinmed.web.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.smartclinmed.web.domain.software.Inquilino;

@Entity
public class Exame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "inquilino_id")
	private Inquilino inquilino;

	private String nome;
	private LocalDate dataLaudo;
	private Boolean disponivelEntrega;
	private LocalDateTime dataExame;
	private LocalDateTime dataEntrega;
	private String prazoEntrega;
	private String arquivo;

	private Paciente paciente;

	public Exame() {

	}

	public Exame(Long id, Inquilino inquilino, String nome, LocalDate dataLaudo, Boolean disponivelEntrega,
			LocalDateTime dataExame, LocalDateTime dataEntrega, String prazoEntrega, String arquivo,
			Paciente paciente) {
		super();
		this.id = id;
		this.inquilino = inquilino;
		this.nome = nome;
		this.dataLaudo = dataLaudo;
		this.disponivelEntrega = disponivelEntrega;
		this.dataExame = dataExame;
		this.dataEntrega = dataEntrega;
		this.prazoEntrega = prazoEntrega;
		this.arquivo = arquivo;
		this.paciente = paciente;
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

	public LocalDate getDataLaudo() {
		return dataLaudo;
	}

	public void setDataLaudo(LocalDate dataLaudo) {
		this.dataLaudo = dataLaudo;
	}

	public Boolean getDisponivelEntrega() {
		return disponivelEntrega;
	}

	public void setDisponivelEntrega(Boolean disponivelEntrega) {
		this.disponivelEntrega = disponivelEntrega;
	}

	public LocalDateTime getDataExame() {
		return dataExame;
	}

	public void setDataExame(LocalDateTime dataExame) {
		this.dataExame = dataExame;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inquilino == null) ? 0 : inquilino.hashCode());
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
		Exame other = (Exame) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inquilino == null) {
			if (other.inquilino != null)
				return false;
		} else if (!inquilino.equals(other.inquilino))
			return false;
		return true;
	}

}
