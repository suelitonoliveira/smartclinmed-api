package br.com.smartclinmed.web.dto.atendimento;

import java.io.Serializable;

public class TriagemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Double peso;
	private Double altura;
	private Double pressaoArterial;
	private Double febre;
	private Double imc;

	public TriagemDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(Double pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
	}

	public Double getFebre() {
		return febre;
	}

	public void setFebre(Double febre) {
		this.febre = febre;
	}

	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}
	
	

}
