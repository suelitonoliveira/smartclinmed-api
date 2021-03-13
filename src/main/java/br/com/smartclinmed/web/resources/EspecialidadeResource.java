package br.com.smartclinmed.web.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.smartclinmed.web.domain.Especialidade;
import br.com.smartclinmed.web.services.EspecialidadeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/especialidades")
public class EspecialidadeResource {

	@Autowired
	private EspecialidadeService service;

	@PreAuthorize("hasAnyRole('Especialidade_List')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Especialidade>> findAll() {
		List<Especialidade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@PreAuthorize("hasAnyRole('Especialidade_List')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Especialidade> findById(@PathVariable Long id){
		Especialidade obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('Especialidade_List')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Especialidade> insert(@RequestBody Especialidade obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();	
		return ResponseEntity.created(uri).build();  
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value="Remover especialidade")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir uma especialidade que possui médicos"),
			@ApiResponse(code = 404, message = "Código inexistente") })
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('Especialidade_Delete')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Especialidade> update(@PathVariable Long id, @RequestBody Especialidade obj){
		obj = service.update(id, obj);
		return ResponseEntity.noContent().build();
		}
	}















