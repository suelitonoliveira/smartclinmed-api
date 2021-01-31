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

import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.dto.PacienteNewDTO;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.repositories.PacienteRepository;
import br.com.smartclinmed.web.services.exceptions.DataIntegrityException;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	public Paciente find(Long id) {
		Optional<Paciente> obj = repo.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Paciente.class.getName());
		}

		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}

	@Transactional
	public Paciente insert(Paciente obj) {
		obj.setDtInclusao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		obj.setId(null);
		obj.setInquilino(obj.getInquilino());
		return repo.save(obj);
	}

	@Transactional
	public Paciente update(Paciente obj) {

		find(obj.getId());
		return repo.save(obj);

	}

	@Transactional
	public void delete(Long id) {
		Paciente obj = find(id);
		try {
			obj.setStatusComum(TipoStatusComum.INATIVO);
			obj.setDtAlteracao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
			repo.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida, itens vinculados");
		}

	}

	public List<Paciente> findAll() {
		return repo.findAll();
	}

	public Page<Paciente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Paciente fromDTO(PacienteNewDTO objDto) {
		return new Paciente(null, null, objDto.getNome(), objDto.getNomeSocial(), objDto.getRg(), objDto.getCpf(),
				objDto.getEmail(), objDto.getDataNascimento(), objDto.getSexo(), objDto.getTipoPaciente(),
				TipoStatusComum.ATIVO, null, objDto.getNomeTitular(), objDto.getIndicacao(), objDto.getEndereco(), null,
				null);
	}

}
