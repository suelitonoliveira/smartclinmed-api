package br.com.smartclinmed.web.resources.atendimento;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import br.com.smartclinmed.web.domain.atendimento.Triagem;
import br.com.smartclinmed.web.dto.atendimento.TriagemDTO;
import br.com.smartclinmed.web.dto.atendimento.TriagemNewDTO;
import br.com.smartclinmed.web.services.atendimento.TriagemService;

@RestController
@RequestMapping(value = "/triagens")
public class TriagemResource {

	@Autowired
	private TriagemService service;

	@PreAuthorize("hasAnyRole('Triagem_List')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Triagem>> findAll() {
		List<Triagem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PreAuthorize("hasAnyRole('Triagem_List')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Optional<Triagem> obj =service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@PreAuthorize("hasAnyRole('Triagem_List')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Triagem>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Triagem> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PreAuthorize("hasAnyRole('Triagem_Insert')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TriagemNewDTO objDto) {
		Triagem obj = service.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('Triagem_Update')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TriagemDTO objDto, @PathVariable Long id) {
		Triagem obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}


}
