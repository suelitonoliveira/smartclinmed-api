package br.com.smartclinmed.web.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.Estado;
import br.com.smartclinmed.web.repositories.EstadoRepository;
import br.com.smartclinmed.web.services.exceptions.DataIntegrityException;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Estado find(Long id) {
		Optional<Estado> obj = repo.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Estado.class.getName());
		}

		return obj.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}

	@Transactional
	public Estado insert(Estado obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	@Transactional
	public Estado update(Estado obj) {
		try {
			find(obj.getId());
			return repo.save(obj);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Not Found");
		}

	}

	@Transactional
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida, itens vinculados");
		}

	}

	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}

	public Page<Estado> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
