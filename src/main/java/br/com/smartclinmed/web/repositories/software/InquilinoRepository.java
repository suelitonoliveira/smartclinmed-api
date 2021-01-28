package br.com.smartclinmed.web.repositories.software;

import java.util.Optional;
import br.com.smartclinmed.web.domain.software.Inquilino;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InquilinoRepository  extends JpaRepository<Inquilino, Long> {

	   @Transactional(readOnly = true)
	    Optional<Inquilino> findById(Long id);

	    @Transactional(readOnly = true)
	    Optional<Inquilino> findBynRegistro(String nRegistro);
}
