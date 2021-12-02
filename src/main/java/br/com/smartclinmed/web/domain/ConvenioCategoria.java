package br.com.smartclinmed.web.domain;

import java.io.Serializable;
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
public class ConvenioCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "inquilino_id")
	private Inquilino inquilino;
	
	private String razaoSocial;
	private String nomeConvenio;
	private String codigoUsoInterno;
	private String contratoVenceEn;
	private String destrato;
	private String reajuste;
	private String inscriçaoEstadual;
	private String email;
	private String site;
	private String cnpj;
	private Integer statusComum;
	private LocalDateTime dtInclusao;
	private LocalDateTime dtAlteracao;

	public ConvenioCategoria() {
	}

	public ConvenioCategoria(Long id, Inquilino inquilino, String razaoSocial, String nomeConvenio,
			String codigoUsoInterno, String contratoVenceEn, String destrato, String reajuste, String inscriçaoEstadual,
			String email, String site, String cnpj, Integer statusComum, LocalDateTime dtInclusao,
			LocalDateTime dtAlteracao) {
		super();
		this.id = id;
		this.inquilino = inquilino;
		this.razaoSocial = razaoSocial;
		this.nomeConvenio = nomeConvenio;
		this.codigoUsoInterno = codigoUsoInterno;
		this.contratoVenceEn = contratoVenceEn;
		this.destrato = destrato;
		this.reajuste = reajuste;
		this.inscriçaoEstadual = inscriçaoEstadual;
		this.email = email;
		this.site = site;
		this.cnpj = cnpj;
		this.statusComum = statusComum;
		this.dtInclusao = dtInclusao;
		this.dtAlteracao = dtAlteracao;
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


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	public String getNomeConvenio() {
		return nomeConvenio;
	}


	public void setNomeConvenio(String nomeConvenio) {
		this.nomeConvenio = nomeConvenio;
	}


	public String getCodigoUsoInterno() {
		return codigoUsoInterno;
	}


	public void setCodigoUsoInterno(String codigoUsoInterno) {
		this.codigoUsoInterno = codigoUsoInterno;
	}


	public String getContratoVenceEn() {
		return contratoVenceEn;
	}


	public void setContratoVenceEn(String contratoVenceEn) {
		this.contratoVenceEn = contratoVenceEn;
	}


	public String getDestrato() {
		return destrato;
	}


	public void setDestrato(String destrato) {
		this.destrato = destrato;
	}


	public String getReajuste() {
		return reajuste;
	}


	public void setReajuste(String reajuste) {
		this.reajuste = reajuste;
	}


	public String getInscriçaoEstadual() {
		return inscriçaoEstadual;
	}


	public void setInscriçaoEstadual(String inscriçaoEstadual) {
		this.inscriçaoEstadual = inscriçaoEstadual;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public Integer getStatusComum() {
		return statusComum;
	}


	public void setStatusComum(Integer statusComum) {
		this.statusComum = statusComum;
	}


	public LocalDateTime getDtInclusao() {
		return dtInclusao;
	}


	public void setDtInclusao(LocalDateTime dtInclusao) {
		this.dtInclusao = dtInclusao;
	}


	public LocalDateTime getDtAlteracao() {
		return dtAlteracao;
	}


	public void setDtAlteracao(LocalDateTime dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
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
		ConvenioCategoria other = (ConvenioCategoria) obj;
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
