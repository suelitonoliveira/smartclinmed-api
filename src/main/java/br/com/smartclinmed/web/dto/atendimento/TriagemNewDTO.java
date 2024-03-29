package br.com.smartclinmed.web.dto.atendimento;

import java.io.Serializable;

public class TriagemNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double peso;
	private Double altura;
	private Double pressaoArterial;
	private Double febre;

	public TriagemNewDTO() {

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

}
