package br.com.smartclinmed.web.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.smartclinmed.web.domain.Especialidade;
import br.com.smartclinmed.web.domain.software.Inquilino;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
	
	
	@Transactional(readOnly = true)
	Especialidade findByIdAndInquilino(Long id, Inquilino inquilino);
	
	@Transactional(readOnly = true)
	List<Especialidade> findByInquilino(Inquilino inquilino);

	@Transactional(readOnly = true)
	Page<Especialidade> findByInquilino(Pageable pageable, Inquilino inquilino);

}
