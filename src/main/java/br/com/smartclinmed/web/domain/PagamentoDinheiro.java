package br.com.smartclinmed.web.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.enums.EstadoPagamento;

@Entity
public class PagamentoDinheiro extends Pagamento {
	private static final long serialVersionUID = 1L;

	private LocalDateTime dataPagamento;

	public PagamentoDinheiro() {

	}

	public PagamentoDinheiro(Long id, Inquilino inquilino, EstadoPagamento estadoPagamento, Agendamento agendamento,
			LocalDateTime dataPagamento) {
		super(id, inquilino, estadoPagamento, agendamento);
		this.dataPagamento = dataPagamento;

	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
