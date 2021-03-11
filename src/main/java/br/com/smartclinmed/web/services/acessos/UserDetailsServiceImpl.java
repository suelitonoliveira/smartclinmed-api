package br.com.smartclinmed.web.services.acessos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.acessos.Permissao;
import br.com.smartclinmed.web.acessos.Usuario;
import br.com.smartclinmed.web.acessos.UsuarioPerfil;
import br.com.smartclinmed.web.repositories.acessos.UsuarioRepository;
import br.com.smartclinmed.web.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = repo.findByEmail(email);

		List<UsuarioPerfil> perfis = user.getPerfis();
		Set<Permissao> hashset = new HashSet<>();

		for (int i = 0; i < perfis.size(); ++i) {
			UsuarioPerfil perfil = perfis.get(i);
			hashset.addAll(perfil.getPermissoes());
		}
		List<Permissao> permissoes = new ArrayList<>(hashset);

		permissoes = permissoes.stream().distinct().collect(Collectors.toList());
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}

		return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getStatusComum().getCod(),
				user.getInquilino(), permissoes);
	}
}