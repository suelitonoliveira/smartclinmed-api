package br.com.smartclinmed.web.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.dto.PacienteDTO;
import br.com.smartclinmed.web.dto.PacienteFindDTO;
import br.com.smartclinmed.web.dto.PacienteNewDTO;
import br.com.smartclinmed.web.services.PacienteService;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteResource {
	@Autowired
	private PacienteService service;

	@PreAuthorize("hasAnyRole('Paciente_List')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PacienteFindDTO>> findAll() {
		List<Paciente> list = service.findAll();
		List<PacienteFindDTO> listDto = list.stream().map(obj -> new PacienteFindDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	@PreAuthorize("hasAnyRole('Paciente_List')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Optional<Paciente> obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}
	@PreAuthorize("hasAnyRole('Paciente_List')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<PacienteFindDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Paciente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PacienteFindDTO> listDto = list.map(obj -> new PacienteFindDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	@PreAuthorize("hasAnyRole('Paciente_Insert')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PacienteNewDTO objDto) {
		Paciente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PreAuthorize("hasAnyRole('Paciente_Update')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PacienteDTO objDto, @PathVariable Long id) {
		Paciente obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	@PreAuthorize("hasAnyRole('Paciente_Delete')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
