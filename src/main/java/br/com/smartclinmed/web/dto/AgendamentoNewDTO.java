package br.com.smartclinmed.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.domain.Pagamento;
import br.com.smartclinmed.web.enums.TipoAgendamento;

public class AgendamentoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime dataAgendamento;
	private Pagamento pagamento;
	private Paciente paciente;
	private TipoAgendamento tipoAgendamento;

	public AgendamentoNewDTO() {

	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public TipoAgendamento getTipoAgendamento() {
		return tipoAgendamento;
	}

	public void setTipoAgendamento(TipoAgendamento  tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}


}
