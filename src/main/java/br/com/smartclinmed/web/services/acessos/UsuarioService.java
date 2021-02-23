package br.com.smartclinmed.web.services.acessos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.acessos.Permissao;
import br.com.smartclinmed.web.acessos.Usuario;
import br.com.smartclinmed.web.acessos.UsuarioPerfil;
import br.com.smartclinmed.web.dto.acessos.UsuarioDTO;
import br.com.smartclinmed.web.dto.acessos.UsuarioNewDTO;
import br.com.smartclinmed.web.dto.acessos.UsuarioPerfilDTO;
import br.com.smartclinmed.web.dto.acessos.UsuarioPermissoesDTO;
import br.com.smartclinmed.web.enums.Perfil;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.repositories.acessos.UsuarioPerfilRepository;
import br.com.smartclinmed.web.repositories.acessos.UsuarioRepository;
import br.com.smartclinmed.web.security.UserSS;
import br.com.smartclinmed.web.services.exceptions.AuthorizationException;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private UsuarioPerfilRepository repoPerfil;

	@Autowired
	private BCryptPasswordEncoder pe;

	public Usuario find(Long id) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = repo.findById(id);
		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Not Found");
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Not Found"));
	}

	public Usuario insert(Usuario obj) {
		UserSS user = UserService.authenticated();
		obj.setId(null);
		obj.setInquilino(user.getInquilino());
		obj.setSenha(pe.encode(obj.getSenha()));
		obj.setDtInclusao(LocalDateTime.now());
		return repo.save(obj);
	}

	public Usuario update(Usuario obj) {
		obj.setSenha(pe.encode(obj.getSenha()));
		return repo.save(obj);
	}

	public void delete(Long id) {
		Usuario user = find(id);
		user.setStatusComum(TipoStatusComum.INATIVO);
		repo.save(user);
	}

	public List<Usuario> findAll() {
		UserSS user = UserService.authenticated();
		return repo.findByInquilino(user.getInquilino());
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByInquilino(user.getInquilino(), pageRequest);
	}

	public Usuario findMyEmail(String email) {
		UserSS user = UserService.authenticated();
		if (!email.equals(user.getUsername())) {
			throw new AuthorizationException("Access denied");
		}

		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException("Not Found");
		}
		return obj;
	}

	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();
		Usuario obj = repo.findByEmailAndInquilino(email, user.getInquilino());
		return obj;
	}

	public List<UsuarioPerfil> findPerfis() {
		List<UsuarioPerfil> perfis = repoPerfil.findAll();
		return perfis;
	}

	public Set<Permissao> findPermissoes() {
		// UserSS userAuth = UserService.authenticated();
		Optional<Usuario> user = repo.findById(userAuth.getId());
		List<UsuarioPerfil> perfis = user.get().getPerfis();
		Set<Permissao> hashsetContains = new HashSet<>();
		for (int i = 0; i < perfis.size(); ++i) {
			UsuarioPerfil perfil = perfis.get(i);
			hashsetContains.addAll(perfil.getPermissoes());
		}

		return hashsetContains;
	}

	public UsuarioPermissoesDTO insertPerfil(UsuarioPermissoesDTO objDto) {
		Optional<Usuario> user = repo.findById(objDto.getIdUsuario());
		if (user == null) {
			throw new ObjectNotFoundException("User Not Found");
		}
		Optional<UsuarioPerfil> perfil = repoPerfil.findById(objDto.getIdPerfil());
		if (perfil == null) {
			throw new ObjectNotFoundException("Profile Not Found");
		}
		user.get().addPerfil(perfil.get());
		return objDto;
	}

	public Usuario findDadosUsuario() {
		// UserSS userAuth = UserService.authenticated();
		Optional<Usuario> user = repo.findById(userAuth.getId());
		return user.get();
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		// UserSS user = UserService.authenticated();
		Optional<Usuario> objAtual = repo.findByIdAndInquilino(objDto.getId(), user.getInquilino());
		Usuario obj = new Usuario(objDto.getId(), user.getInquilino(), objAtual.get().getEmail(),
				objDto.getStatusComum(), objDto, objAtual.get().getDtInclusao(), LocalDateTime.now());
		obj.setPerfis(objDto.getPerfis());
		return obj;
	}

	public Usuario fromDTO(UsuarioPerfilDTO objDto) {
		// UserSS user = UserService.authenticated();
		Optional<Usuario> objAtual = repo.findByIdAndInquilino(user.getId(), user.getInquilino());
		Usuario obj = new Usuario(user.getId(), user.getInquilino(), objAtual.get().getEmail(),
				objAtual.get().getStatusComum(), objDto.getSenha(), objAtual.get().getDtInclusao(),
				LocalDateTime.now());
		obj.setPerfis(objAtual.get().getPerfis());
		return obj;
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		// UserSS user = UserService.authenticated();
		Usuario obj = new Usuario(null, user.getInquilino(), objDto.getEmail(), TipoStatusComum.ATIVO,
				objDto.getSenha(), LocalDateTime.now(), null);
		obj.setPerfis(objDto.getPerfis());
		return obj;
	}

}
