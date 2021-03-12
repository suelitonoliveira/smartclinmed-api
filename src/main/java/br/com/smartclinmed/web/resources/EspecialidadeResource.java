package br.com.smartclinmed.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.smartclinmed.web.domain.Especialidade;
import br.com.smartclinmed.web.services.EspecialidadeService;

@RestController
@RequestMapping(value = "/especialidades")
public class EspecialidadeResource {

	@Autowired
	private EspecialidadeService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Especialidade>> findAll() {
		List<Especialidade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
