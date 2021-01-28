package br.com.smartclinmed.web.services.software;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.enums.TipoStatusComum;

import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;
import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.repositories.software.InquilinoRepository;

@Service
public class InquilinoService {
	@Autowired
	private InquilinoRepository repo;
	
	public Inquilino find(Long id) {
		Optional<Inquilino> obj = repo.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Not Found");
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Not Found"));
	}
	public List<Inquilino> findAll() {
		return repo.findAll();
	}
	
	public Page<Inquilino> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	@Transactional
	public Inquilino insert(Inquilino obj) {
		obj.setId(null);
		obj.setDtInclusao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		return repo.save(obj);
	}
	
	@Transactional
	public Inquilino update(Inquilino obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	@Transactional
	public void delete(Long id) {
		Inquilino obj = find(id);
		obj.setDtAlteracao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		obj.setStatusComum(TipoStatusComum.INATIVO);
	}

	
}
