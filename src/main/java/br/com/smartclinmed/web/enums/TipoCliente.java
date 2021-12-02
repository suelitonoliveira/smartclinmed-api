package br.com.smartclinmed.web.enums;


public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa fisica"), 
	PESSOA_JURIDICA(2, "Pessoa juridica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer sexo) {
		if (sexo == null) {
			return null;
		}

		for (TipoCliente x : TipoCliente.values()) {
			if (sexo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID");
	}


	
}
