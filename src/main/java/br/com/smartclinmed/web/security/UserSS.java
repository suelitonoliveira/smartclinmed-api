package br.com.smartclinmed.web.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.smartclinmed.web.acessos.Permissao;
import br.com.smartclinmed.web.domain.software.Inquilino;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String senha;
	private Integer statusComum;
	private Collection<? extends GrantedAuthority> authorities;
	private Inquilino inquilino;

	public UserSS() {
	}

	public UserSS(Integer id, String email, String senha, Integer statusComum, Inquilino inquilino,
			List<Permissao> permissoes) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.statusComum = statusComum;
		this.inquilino = inquilino;
		this.authorities = permissoes.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toList());

	}

	public Integer getId() {
		return id;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public Integer getStatusComum() {
		return statusComum;
	}

	public void setStatusComum(Integer statusComum) {
		this.statusComum = statusComum;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(Permissao permissao) {
		return getAuthorities().contains(new SimpleGrantedAuthority(permissao.getDescricao()));
	}
}