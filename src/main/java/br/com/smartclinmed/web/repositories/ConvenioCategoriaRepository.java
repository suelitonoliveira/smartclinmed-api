package br.com.smartclinmed.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.smartclinmed.web.domain.ConvenioCategoria;

@Repository
public interface ConvenioCategoriaRepository extends JpaRepository<ConvenioCategoria, Long> {
	
	

}
