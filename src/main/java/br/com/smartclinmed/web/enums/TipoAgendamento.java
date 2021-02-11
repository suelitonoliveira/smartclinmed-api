package br.com.smartclinmed.web.enums;


public enum TipoAgendamento {

	CONSULTA(1, "Consulta"), 
	EXAME(2, "Exame"),
	PROCEDIMENTO(3, "Procedimento"); 

	private int cod;
	private String descricao;

	private TipoAgendamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoAgendamento toEnum(Integer sexo) {
		if (sexo == null) {
			return null;
		}

		for (TipoAgendamento x : TipoAgendamento.values()) {
			if (sexo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID");
	}


	
}
