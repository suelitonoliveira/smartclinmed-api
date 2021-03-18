package br.com.smartclinmed.web.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.domain.Profissional;
import br.com.smartclinmed.web.repositories.ProfissionalRepository;
import br.com.smartclinmed.web.security.UserSS;
import br.com.smartclinmed.web.services.acessos.UserService;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository repo;

	public Optional<Profissional> find(Long id) {
		UserSS user = UserService.authenticated();
		Optional<Profissional> obj = Optional.ofNullable(repo.findByIdAndInquilino(id, user.getInquilino()));

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Paciente.class.getName());
		}
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException("Not Found")));
	}

	public List<Profissional> findAll() {
		UserSS user = UserService.authenticated();
		return repo.findByInquilino(user.getInquilino());
	}

	public Page<Profissional> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByInquilino(pageRequest, user.getInquilino());
	}

	
}
