package br.com.smartclinmed.web.enums;

public enum TipoProfissional {

	MEDICO(1, "Médico"), TECNICO(2, "Técnico"), FONO(3, "Fono");

	private int cod;
	private String descricao;

	private TipoProfissional(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoProfissional toEnum(Integer sexo) {
		if (sexo == null) {
			return null;
		}

		for (TipoProfissional x : TipoProfissional.values()) {
			if (sexo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID");
	}

}
