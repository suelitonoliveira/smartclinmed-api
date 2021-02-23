package br.com.smartclinmed.web.resources.acessos;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
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

import br.com.smartclinmed.web.acessos.Permissao;
import br.com.smartclinmed.web.acessos.Usuario;
import br.com.smartclinmed.web.acessos.UsuarioPerfil;
import br.com.smartclinmed.web.dto.acessos.UsuarioDTO;
import br.com.smartclinmed.web.dto.acessos.UsuarioNewDTO;
import br.com.smartclinmed.web.dto.acessos.UsuarioPerfilDTO;
import br.com.smartclinmed.web.dto.acessos.UsuarioPermissoesDTO;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.services.acessos.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@Transactional
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Usuario> find(@RequestParam(value = "value") String email) {
		Usuario obj = service.findMyEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('Usuario_List')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Optional<Usuario> obj = Optional.ofNullable(service.find(id));
		return ResponseEntity.ok(obj);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Usuario> obj = service.findAll();
		return ResponseEntity.ok(obj);
	}

	@RequestMapping(value = "/dados_Usuario", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findDadosUsuario() {
		Usuario user = service.findDadosUsuario();
		return ResponseEntity.ok().body(user);
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Usuario>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Usuario> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/permissoes", method = RequestMethod.GET)
	public ResponseEntity<Set<Permissao>> findPermissoes() {
		Set<Permissao> list = service.findPermissoes();
		return ResponseEntity.ok().body(list);
	}

	@PreAuthorize("hasAnyRole('Usuario_Update')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Integer id) {
		objDto.setId(id);
		Usuario obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Usuario obj = service.find(id);
		obj.setStatusComum(TipoStatusComum.INATIVO);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('Usuario_Insert')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDTO objDto) {
		Usuario obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/profile", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProfile(@Valid @RequestBody UsuarioPerfilDTO objDto) {
		Usuario obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('Usuario_Update')")
	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	public ResponseEntity<Void> insertPermissao(@Valid @RequestBody UsuarioPermissoesDTO obj) {
		obj = service.insertPerfil(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('Perfis_Usuario')")
	@RequestMapping(value = "/perfis", method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioPerfil>> findPerfis() {
		List<UsuarioPerfil> list = service.findPerfis();
		return ResponseEntity.ok().body(list);
	}

	// @RequestMapping(value = "/imagem", method = RequestMethod.POST)
	// public ResponseEntity<Void> uploadImage(@RequestParam MultipartFile obj) {
	// obj = service.insertPerfil(obj);
	// URI uri =
	// ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario())
	// .toUri();
	// return ResponseEntity.created(uri).build();
	// }

}
