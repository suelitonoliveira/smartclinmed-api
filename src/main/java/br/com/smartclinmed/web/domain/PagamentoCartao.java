package br.com.smartclinmed.web.domain;

import java.time.LocalDateTime;

import br.com.smartclinmed.web.domain.atendimento.Agendamento;
import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.enums.EstadoPagamento;

public class PagamentoCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private LocalDateTime dataPagamento;
	private Integer numeroParcelas;

	public PagamentoCartao() {

	}

	
	public PagamentoCartao(Long id, Inquilino inquilino, EstadoPagamento estadoPagamento, Agendamento agendamento, LocalDateTime dataPagamento, Integer numeroParcelas) {
		super(id, inquilino, estadoPagamento, agendamento);
		this.dataPagamento = dataPagamento;
		this.numeroParcelas = numeroParcelas;
	}


	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}
