package br.com.smartclinmed.web.services.atendimento;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.atendimento.Triagem;
import br.com.smartclinmed.web.dto.atendimento.TriagemDTO;
import br.com.smartclinmed.web.dto.atendimento.TriagemNewDTO;
import br.com.smartclinmed.web.repositories.atendimento.TriagemRepository;
import br.com.smartclinmed.web.security.UserSS;
import br.com.smartclinmed.web.services.acessos.UserService;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@Service
public class TriagemService {
	@Autowired
	private TriagemRepository repo;

	public Optional<Triagem> find(Long id) {
		UserSS user = UserService.authenticated();
		Optional<Triagem> obj = Optional.ofNullable(repo.findByIdAndInquilino(id, user.getInquilino()));

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Not Found");
		}
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException("Not Found")));
	}

	public List<Triagem> findAll() {
		UserSS user = UserService.authenticated();
		return repo.findByInquilino(user.getInquilino());
	}

	public Page<Triagem> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByInquilino(pageRequest, user.getInquilino());
	}

	@Transactional
	public Triagem insert(TriagemNewDTO objDto) {
		Triagem obj = fromDTO(objDto);
		obj = repo.save(obj);
		return obj;
	}

	@Transactional
	public Triagem update(Triagem obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	public Triagem fromDTO(TriagemNewDTO objDto) {
		UserSS user = UserService.authenticated();
		Triagem obj = new Triagem(null, user.getInquilino(), objDto.getPeso(), objDto.getAltura(),
				objDto.getPressaoArterial(), objDto.getFebre(), null);
		obj.setImc(cacularIMC(obj));
		return obj;
	}

	private Double cacularIMC(Triagem triagem) {
		return triagem.cacularIMC();
	}
	
	
	public Triagem fromDto(TriagemDTO objDto) {
		UserSS user = UserService.authenticated();
		Triagem obj = new Triagem(objDto.getId(), user.getInquilino(), objDto.getPeso(), objDto.getAltura(),
				objDto.getPressaoArterial(), objDto.getFebre(), objDto.getImc());

		return obj;
	}

}
