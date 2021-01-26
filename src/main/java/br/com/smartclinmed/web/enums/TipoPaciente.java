package br.com.smartclinmed.web.enums;


public enum TipoPaciente {

	TITULAR(1, "Titular"), 
	DEPENDENTE(2, "Dependente");

	private int cod;
	private String descricao;

	private TipoPaciente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoPaciente toEnum(Integer sexo) {
		if (sexo == null) {
			return null;
		}

		for (TipoPaciente x : TipoPaciente.values()) {
			if (sexo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID");
	}


	
}
