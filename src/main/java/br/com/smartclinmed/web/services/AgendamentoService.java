package br.com.smartclinmed.web.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.Agendamento;
import br.com.smartclinmed.web.dto.AgendamentoDTO;
import br.com.smartclinmed.web.dto.AgendamentoNewDTO;
import br.com.smartclinmed.web.repositories.AgendamentoRepository;
import br.com.smartclinmed.web.services.email.EmailService;
import br.com.smartclinmed.web.services.exceptions.DataIntegrityException;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repo;
	
	@Autowired
	private EmailService emailService;

	public Agendamento find(Long id) {
		Optional<Agendamento> obj = repo.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Agendamento.class.getName());
		}

		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}

	@Transactional
	public Agendamento insert(Agendamento obj) {
		obj.setDtInclusao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		obj.setId(null);
		obj.setInquilino(obj.getInquilino());
		emailService.sendOrderConfirmationEmail(obj);
		return repo.save(obj);
	}

	@Transactional
	public Agendamento update(Agendamento obj) {

		find(obj.getId());
		return repo.save(obj);

	}

	@Transactional
	public void delete(Long id) {
		Agendamento obj = find(id);
		try {
			obj.setDtAlteracao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
			repo.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida, itens vinculados");
		}

	}

	public List<Agendamento> findAll() {
		return repo.findAll();
	}

	public Page<Agendamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Agendamento fromDTO(AgendamentoNewDTO objDto) {
		return new Agendamento(null, null, objDto.getDataAgendamento(), objDto.getPagamento(), objDto.getPaciente(),
				objDto.getTipoAgendamento(), LocalDateTime.now(), null);
	}

	public Agendamento fromDto(AgendamentoDTO objDto) {
		Optional<Agendamento> objAtual = Optional.ofNullable(find(objDto.getId()));
		Agendamento obj = new Agendamento(objDto.getId(), null, objDto.getDataAgendamento(), objDto.getPagamento(),
				objDto.getPaciente(), objDto.getTipoAgendamento(), objAtual.get().getDtInclusao(), LocalDateTime.now());
		
		return obj;

	}
}
