package br.com.smartclinmed.web.enums;


public enum TipoStatusComum {

	ATIVO(1, "Ativo"), 
	INATIVO(2, "Inativo");

	private int cod;
	private String descricao;

	private TipoStatusComum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoStatusComum toEnum(Integer sexo) {
		if (sexo == null) {
			return null;
		}

		for (TipoStatusComum x : TipoStatusComum.values()) {
			if (sexo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID");
	}


	
}
