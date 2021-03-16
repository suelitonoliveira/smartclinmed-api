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
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import br.com.smartclinmed.web.domain.Especialidade;
import br.com.smartclinmed.web.domain.Paciente;
//import br.com.smartclinmed.web.domain.atendimento.Triagem;
import br.com.smartclinmed.web.repositories.EspecialidadeRepository;
import br.com.smartclinmed.web.security.UserSS;
import br.com.smartclinmed.web.services.acessos.UserService;
import br.com.smartclinmed.web.services.exceptions.DataIntegrityException;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository repo;
	
	public Optional<Especialidade> find(Long id) {
		UserSS user = UserService.authenticated();
		Optional<Especialidade> obj = Optional.ofNullable(repo.findByIdAndInquilino(id, user.getInquilino()));

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Paciente.class.getName());
		}
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException("Not Found")));
	}
	public List<Especialidade> findAll(){
		UserSS user = UserService.authenticated();
		return repo.findByInquilino(user.getInquilino());
	}
	
	public Page<Especialidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByInquilino(pageRequest, user.getInquilino());
	}
	
	public Especialidade insert(Especialidade obj) {
		UserSS user = UserService.authenticated();
		obj.setId(null);
		obj.setInquilino(user.getInquilino());
		return repo.save(obj);
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
	
	@Transactional
	public Especialidade update(Especialidade obj) {
		try {
			return repo.save(obj);
		}catch (Exception e) {
			throw new ObjectNotFoundException("Not Found");
		}
	}

	/*
	 public Especialidade update(Long id, Especialidade obj) {
				Especialidade entity = repo.getOne(id);
				updateData(entity, obj);
				return repo.save(entity);
		
	 }	
	 
	private void updateData(Especialidade entity, Especialidade obj) {
		entity.setNome(obj.getNome());
		entity.setCbosTiss2(obj.getCbosTiss2());
		entity.setCbosTiss3(obj.getCbosTiss3());
	}*/
	
}
