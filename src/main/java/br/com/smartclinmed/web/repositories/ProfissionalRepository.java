package br.com.smartclinmed.web.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.smartclinmed.web.domain.Profissional;
import br.com.smartclinmed.web.domain.software.Inquilino;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
	
	
	@Transactional(readOnly = true)
	Profissional findByIdAndInquilino(Long id, Inquilino inquilino);
	
	@Transactional(readOnly = true)
	List<Profissional> findByInquilino(Inquilino inquilino);

	@Transactional(readOnly = true)
	Page<Profissional> findByInquilino(Pageable pageable, Inquilino inquilino);

}
