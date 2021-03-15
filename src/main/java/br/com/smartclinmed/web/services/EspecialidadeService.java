package br.com.smartclinmed.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import br.com.smartclinmed.web.domain.Especialidade;
import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.repositories.EspecialidadeRepository;
import br.com.smartclinmed.web.security.UserSS;
import br.com.smartclinmed.web.services.acessos.UserService;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository repo;
	 
	public List<Especialidade> findAll(){
		return repo.findAll();
	}
	
	public Optional<Especialidade> find(Long id) {
		UserSS user = UserService.authenticated();
		Optional<Especialidade> obj = Optional.ofNullable(repo.findByIdAndInquilino(id, user.getInquilino()));

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrato! ID: " + id + ", Tipo: " + Paciente.class.getName());
		}
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException("Not Found")));
	}
	
	
	/*
	 * public Especialidade findById(Long id) { Optional<Especialidade> obj =
	 * repo.findById(id); return obj.get(); }
	 */
	
	public Especialidade insert(Especialidade obj) {
		UserSS user = UserService.authenticated();
		obj.setId(null);
		obj.setInquilino(user.getInquilino());
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	 public Especialidade update(Long id, Especialidade obj){
			//try {
				Especialidade entity = repo.getOne(id);
				updateData(entity, obj);
				return repo.save(entity);
			//} catch (EntityNotFoundException e) {
				//throw new ResourceNotFoundException(id);
			
	 }

	private void updateData(Especialidade entity, Especialidade obj) {
		entity.setNome(obj.getNome());
		entity.setCbosTiss2(obj.getCbosTiss2());
		entity.setCbosTiss3(obj.getCbosTiss3());
	}
	
}
