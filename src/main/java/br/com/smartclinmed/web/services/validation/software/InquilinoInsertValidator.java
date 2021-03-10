package br.com.smartclinmed.web.services.validation.software;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.dto.software.InquilinoNewDTO;
import br.com.smartclinmed.web.enums.TipoCliente;
import br.com.smartclinmed.web.repositories.software.InquilinoRepository;
import br.com.smartclinmed.web.resources.exception.FieldMessage;

public class InquilinoInsertValidator implements ConstraintValidator<InquilinoInsert, InquilinoNewDTO> {
	
	@Autowired
	private InquilinoRepository repo;

	@Override
	public void initialize(InquilinoInsert ann) {
	}

	@Override
	public boolean isValid(InquilinoNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipoCliente() == null) {
			list.add(new FieldMessage("TipoCliente", "Mandatory field"));
		}

		if (objDto.getnRegistro() == null) {
			list.add(new FieldMessage("nRegistro", "Mandatory field"));
		}else {
			if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA)) {
				boolean validadorCPF = new CPFValidator().isEligible(objDto.getnRegistro());
				if (!validadorCPF) {
					list.add(new FieldMessage("nRegistro", "CPF Invalid "));
				}
			}
			if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA)) {
				boolean validadorCNPJ = new CNPJValidator().isEligible(objDto.getnRegistro());
				if (!validadorCNPJ) {
					list.add(new FieldMessage("nRegistro", "CNPJ Invalid "));
				}
			}
		}

		if (objDto.getTipoContratacao() == null) {
			list.add(new FieldMessage("TipoContratacao", "Mandatory field"));
		}

		if (objDto.getRazaoSocial() == null || objDto.getRazaoSocial().length() < 5) {
			list.add(new FieldMessage("RazaoSocial", "Mandatory field, minimum number of characters 5"));
		}

		if (objDto.getFantasia() == null || objDto.getFantasia().length() < 5) {
			list.add(new FieldMessage("Fantasia", "Mandatory field, minimum number of characters 5"));
		}

		Optional<Inquilino> aux = repo.findBynRegistro(objDto.getnRegistro());
		if (!aux.isEmpty()) {
			list.add(new FieldMessage("nRegistro", "NRegistro already registered"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}