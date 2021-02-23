package br.com.smartclinmed.web.services.acessos;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.smartclinmed.web.security.UserSS;

public class UserService {

	
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
