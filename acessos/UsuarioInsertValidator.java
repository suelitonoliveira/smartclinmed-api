package com.membbers.web.services.validation.acessos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.membbers.web.domain.acessos.Usuario;
import com.membbers.web.domain.pessoal.Pessoa;
import com.membbers.web.dto.acessos.UsuarioNewDTO;
import com.membbers.web.repositories.PessoaRepository;
import com.membbers.web.repositories.acessos.UsuarioRepository;
import com.membbers.web.resources.exception.FieldMessage;
import com.membbers.web.security.UserSS;
import com.membbers.web.services.acessos.UserService;

import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {
	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private PessoaRepository repoPessoa;

	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		UserSS user = UserService.authenticated();

		if (objDto.getEmail() == null) {
			list.add(new FieldMessage("Email", "Mandatory field"));
		}
		Usuario objEmail = repo.findByEmail(objDto.getEmail());
		if (objEmail != null) {
			list.add(new FieldMessage("Email", "Already registered"));
		}

		Usuario objUserPessoa = repo.findByPessoaAndInquilino(objDto.getPessoa(), user.getInquilino());
		if (objUserPessoa != null) {
			list.add(new FieldMessage("Pessoa", "Already registered"));
		}

		if (objDto.getPessoa() == null) {
			list.add(new FieldMessage("Pessoa", "Mandatory field"));
		} else {
			Pessoa objPessoa = repoPessoa.findByIdAndInquilino(objDto.getPessoa().getId(), user.getInquilino());
			if (objPessoa == null) {
				list.add(new FieldMessage("Pessoa", "Record not found"));
			}
		}

		if (objDto.getSenha() == null) {
			list.add(new FieldMessage("Senha", "Mandatory field"));
		} else {
			if (objDto.getSenha().length() < 6) {
				list.add(new FieldMessage("Senha", "Password must contain at least six characters"));
			}
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}