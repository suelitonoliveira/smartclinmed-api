package br.com.smartclinmed.web.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.smartclinmed.web.domain.Profissional;
import br.com.smartclinmed.web.services.ProfissionalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/profissionais")
public class ProfissionalResource {

	@Autowired
	private ProfissionalService service;

	@PreAuthorize("hasAnyRole('Profissional_List')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Profissional>> findAll() {
		List<Profissional> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@PreAuthorize("hasAnyRole('Profissional_List')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id){
		Optional<Profissional> obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('Profissional_List')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Profissional>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Profissional> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	
	@PreAuthorize("hasAnyRole('Profissional_List')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Profissional> insert(@RequestBody Profissional obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();	
		return ResponseEntity.created(uri).build();  
	}
	

	
	@PreAuthorize("hasAnyRole('Profissional_Delete')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation ( value = " Remover Profissional " )
	@ApiResponses ( value  = {
			@ApiResponse ( code  =  400 , message  =  " Não é possível excluir uma Profissional que possui especialidades " ),
			@ApiResponse ( code  =  404 , message  =  " Código inexistente " )})
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('Especialidade_Update')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Profissional> update(@PathVariable Long id, @RequestBody Profissional obj){
		obj = service.update(id, obj);
		return ResponseEntity.noContent().build();
		}
		
	}















