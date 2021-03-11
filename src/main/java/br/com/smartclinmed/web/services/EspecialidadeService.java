package br.com.smartclinmed.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.smartclinmed.web.domain.Especialidade;
import br.com.smartclinmed.web.repositories.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository repository;
	 
	public List<Especialidade> findAll(){
		return repository.findAll();
	}

}
