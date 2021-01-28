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

import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.repositories.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository repo;

	public Paciente find(Long id) {
		Optional<Paciente> obj = repo.findById(id);
		
		
		/*
		 * if (obj.isEmpty()) { throw new
		 * ObjectNotFoundException("Objeto não encontrato! ID: " + id + ", Tipo: " +
		 * Paciente.class.getName()); }
		 */
		return obj.orElse(null);  //.orElseThrow(() -> new ObjectNotFoundException("Not found"));
	}
	@Transactional
	public Paciente insert(Paciente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	@Transactional
	public Paciente update(Paciente obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	@Transactional
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			//throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos associados");
		}

	}
	
	public List<Paciente> findAll(){
		return repo.findAll();
	}
	
	public Page<Paciente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	/*
	 * public Paciente fromDTO(PacienteDTO objDto) { return new
	 * Paciente(objDto.getId(), objDto.getNome()); }
	 */
	
}
