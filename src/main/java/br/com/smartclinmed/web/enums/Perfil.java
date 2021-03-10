package br.com.smartclinmed.web.enums;


public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), 
	PACIENTE(1, "ROLE_PACIENTE"), 
	USUARIO(2, "ROLE_USUARIO");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer sexo) {
		if (sexo == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (sexo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID");
	}


	
}
