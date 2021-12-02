package com.membbers.web.services.validation.acessos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.membbers.web.dto.acessos.UsuarioPerfilDTO;
import com.membbers.web.resources.exception.FieldMessage;

public class UsuarioUpdateProfileValidator implements ConstraintValidator<UsuarioUpdateProfile, UsuarioPerfilDTO> {

	@Override
	public void initialize(UsuarioUpdateProfile ann) {
	}

	@Override
	public boolean isValid(UsuarioPerfilDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getSenha().length() < 6) {
			list.add(new FieldMessage("senha", "Password must contain at least six characters"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}