package br.com.smartclinmed.web.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;



import br.com.smartclinmed.web.domain.Especialidade;
import br.com.smartclinmed.web.repositories.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository repository;
	 
	public List<Especialidade> findAll(){
		return repository.findAll();
	}
	
	
	public Especialidade findById(Long id) {
		Optional<Especialidade> obj = repository.findById(id);
		return obj.get();
	}
	
	public Especialidade insert(Especialidade obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	 public Especialidade update(Long id, Especialidade obj){
			//try {
				Especialidade entity = repository.getOne(id);
				updateData(entity, obj);
				return repository.save(entity);
			//} catch (EntityNotFoundException e) {
				//throw new ResourceNotFoundException(id);
			
	 }

	private void updateData(Especialidade entity, Especialidade obj) {
		entity.setNome(obj.getNome());
		entity.setCbosTiss2(obj.getCbosTiss2());
		entity.setCbosTiss3(obj.getCbosTiss3());
	}
	
}
