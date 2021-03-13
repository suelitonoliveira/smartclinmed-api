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
import br.com.smartclinmed.web.dto.PacienteDTO;
import br.com.smartclinmed.web.dto.PacienteNewDTO;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.repositories.PacienteRepository;
import br.com.smartclinmed.web.security.UserSS;
import br.com.smartclinmed.web.services.acessos.UserService;
import br.com.smartclinmed.web.services.exceptions.DataIntegrityException;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	public Optional<Paciente> find(Long id) {
		UserSS user = UserService.authenticated();
		Optional<Paciente> obj = Optional.ofNullable(repo.findByIdAndInquilino(id, user.getInquilino()));

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Paciente.class.getName());
		}

		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException("Not Found")));
	}

	public List<Paciente> findAll() {
		UserSS user = UserService.authenticated();
		return repo.findByInquilino(user.getInquilino());
	}

	public Page<Paciente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByInquilino(pageRequest, user.getInquilino());
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
		obj.setDtAlteracao(LocalDateTime.now());
		find(obj.getId());
		return repo.save(obj);

	}

	@Transactional
	public void delete(Long id) {
		Optional<Paciente> obj = find(id);
		try {
			obj.get().setStatusComum(TipoStatusComum.INATIVO);
			obj.get().setDtAlteracao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
			repo.save(obj.get());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida, itens vinculados");
		}

	}

	public Paciente fromDTO(PacienteNewDTO objDto) {
		UserSS user = UserService.authenticated();
		Paciente obj = new Paciente(null, user.getInquilino(), objDto.getNome(), objDto.getNomeSocial(), objDto.getRg(),
				objDto.getCpf(), objDto.getEmail(), objDto.getDataNascimento(), objDto.getSexo(),
				objDto.getTipoPaciente(), TipoStatusComum.ATIVO, null, objDto.getNomeTitular(), objDto.getIndicacao(),
				objDto.getEndereco(), LocalDateTime.now(), null);
		return obj;
	}

	public Paciente fromDto(PacienteDTO objDto) {
		UserSS user = UserService.authenticated();
		Optional<Paciente> objAtual = find(objDto.getId());
		Paciente obj = new Paciente(objDto.getId(), user.getInquilino(), objDto.getNome(), objDto.getNomeSocial(),
				objDto.getRg(), objDto.getCpf(), objDto.getEmail(), objDto.getDataNascimento(), objDto.getSexo(),
				objDto.getTipoPaciente(), objDto.getStatusComum(), null, objDto.getNomeTitular(), objDto.getIndicacao(),
				objDto.getEndereco(), objAtual.get().getDtInclusao(), LocalDateTime.now());
		return obj;
	}

}
