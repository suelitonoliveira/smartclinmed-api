package br.com.smartclinmed.web.repositories.atendimento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.smartclinmed.web.domain.software.Inquilino;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import br.com.smartclinmed.web.domain.atendimento.Triagem;

@Repository
public interface TriagemRepository extends JpaRepository<Triagem, Long> {
	
	
	@Transactional(readOnly = true)
	Triagem findByIdAndInquilino(Long id, Inquilino inquilino);
	
	@Transactional(readOnly = true)
	List<Triagem> findByInquilino(Inquilino inquilino);

	@Transactional(readOnly = true)
	Page<Triagem> findByInquilino(Pageable pageable, Inquilino inquilino);

}
