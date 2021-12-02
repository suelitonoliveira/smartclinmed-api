package br.com.smartclinmed.web.services.software;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.dto.software.InquilinoDTO;
import br.com.smartclinmed.web.dto.software.InquilinoNewDTO;
import br.com.smartclinmed.web.enums.TipoStatusComum;
import br.com.smartclinmed.web.repositories.software.InquilinoRepository;
import br.com.smartclinmed.web.services.exceptions.DataIntegrityException;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class InquilinoService {
	@Autowired
	private InquilinoRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public Inquilino find(Integer id) {
		Optional<Inquilino> obj = repo.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Not Found");
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Not Found"));
	}

	public List<Inquilino> findAll() {
		return repo.findAll();
	}

	public Page<Inquilino> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public Inquilino insert(Inquilino obj) {
		obj.setId(null);
		obj.setDtInclusao(LocalDateTime.now());
		return repo.save(obj);
	}

	@Transactional
	public Inquilino update(Inquilino obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public Inquilino fromDTO(InquilinoNewDTO objDto) {
		Inquilino obj = new Inquilino(null, objDto.getFantasia(), objDto.getRazaoSocial(),
				objDto.getTipoCliente(), TipoStatusComum.ATIVO, objDto.getTipoContratacao(), objDto.getnRegistro(),
				objDto.getImagem(), objDto.getImagem64(), objDto.getEmail(),pe.encode(objDto.getSenha()), LocalDateTime.now(), null );
		obj.getTelefones().addAll(objDto.getTelefones());
		return obj;
	}
	
	public Inquilino fromDTO(InquilinoDTO objDto) {
		Optional<Inquilino> objAtual = Optional.ofNullable(find(objDto.getId()));
		Inquilino obj = new Inquilino(objDto.getId(), objDto.getFantasia(), objDto.getRazaoSocial(),
				null, objDto.getStatusComum(), objDto.getTipoContratacao(), null,
				objDto.getImagem(), objDto.getImagem64(),objDto.getEmail(),null,objAtual.get().getDtInclusao(), LocalDateTime.now());
		obj.getTelefones().addAll(objDto.getTelefones());
		return obj;
	}

	@Transactional
	public void delete(Integer id) {
		try {
			Inquilino obj = find(id);
			obj.setDtAlteracao(LocalDateTime.now());
			obj.setStatusComum(TipoStatusComum.INATIVO);

		} catch (Exception e) {
			throw new DataIntegrityException("Exclusão não permitida, itens vinculados.");
		}

	}

}
