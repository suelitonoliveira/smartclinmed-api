package br.com.smartclinmed.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.enums.TipoAgendamento;

@Entity
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "inquilino_id")
	private Inquilino inquilino;

	private LocalDateTime dataAgendamento;
	private Pagamento pagamento;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	private Integer tipoAgendamento;

	private LocalDateTime dtInclusao;
	private LocalDateTime dtAlteracao;

	public Agendamento() {

	}

	public Agendamento(Long id, Inquilino inquilino, LocalDateTime dataAgendamento, Pagamento pagamento,
			Paciente paciente, TipoAgendamento tipoAgendamento, LocalDateTime dtInclusao, LocalDateTime dtAlteracao) {
		super();
		this.id = id;
		this.inquilino = inquilino;
		this.setDataAgendamento(dataAgendamento);
		this.pagamento = pagamento;
		this.paciente = paciente;
		this.tipoAgendamento = (tipoAgendamento == null) ? 1 : tipoAgendamento.getCod();
		this.dtInclusao = dtInclusao;
		this.dtAlteracao = dtAlteracao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
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
		return TipoAgendamento.toEnum(tipoAgendamento);
	}

	public void setTipoAgendamento(TipoAgendamento tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento.getCod();
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public LocalDateTime getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(LocalDateTime dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public LocalDateTime getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(LocalDateTime dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inquilino == null) ? 0 : inquilino.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inquilino == null) {
			if (other.inquilino != null)
				return false;
		} else if (!inquilino.equals(other.inquilino))
			return false;
		return true;
	}

	/*
	 * @Override public String toString() { StringBuilder builder = new
	 * StringBuilder(); builder.append("Agendamento: "); builder.append(getId());
	 * builder.append(", inquilino: "); builder.append(getInquilino());
	 * builder.append(", Data do Agendamento: ");
	 * builder.append(getDataAgendamento()); builder.append(", paciente: ");
	 * builder.append(getPaciente().getNome());
	 * builder.append(", Tipo do Agendamento: ");
	 * builder.append(getTipoAgendamento());
	 * builder.append("Situação do Pagamento"); //
	 * builder.append(getPagamento().getEstadoPagamento().getDescricao()); return
	 * builder.toString(); }
	 */
}
