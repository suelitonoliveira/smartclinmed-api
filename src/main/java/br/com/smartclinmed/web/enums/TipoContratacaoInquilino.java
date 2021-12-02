package br.com.smartclinmed.web.enums;


public enum TipoContratacaoInquilino {

	FULL(1, "Pacote completo"), 
	BASICO_WHATSAPP(2,"Pacote com confirmação via Whatsapp"),
	BASICO_WHATSAPP_NFE(3,"Pacote com Whatsapp E NFE"),
	BASICO_SMS(4,"Pacote com confirmação via SMS"),
	BASICO_SMS_NFE(5,"Pacote com SMS E NFE"),
	BASICO_NFE(6,"Pacote com NotaFiscal"),
	BASICO_WHATSAPP_SMS(7,"Pacote com whatsapp e SMS"),
	BASICO(8, "Pacote básico");

	private int cod;
	private String descricao;

	private TipoContratacaoInquilino(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoContratacaoInquilino toEnum(Integer sexo) {
		if (sexo == null) {
			return null;
		}

		for (TipoContratacaoInquilino x : TipoContratacaoInquilino.values()) {
			if (sexo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID");
	}


	
}
