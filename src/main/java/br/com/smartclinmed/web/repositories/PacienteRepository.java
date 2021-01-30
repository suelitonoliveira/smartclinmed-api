package br.com.smartclinmed.web.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import br.com.smartclinmed.web.domain.Paciente;
import br.com.smartclinmed.web.domain.software.Inquilino;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	@Transactional(readOnly = true)
	Paciente findByIdAndInquilino(Long id, Inquilino inquilino);
	
	@Transactional(readOnly = true)
	List<Paciente> findByInquilino(Inquilino inquilino);

	@Transactional(readOnly = true)
	Page<Paciente> findByInquilino(Pageable pageable, Inquilino inquilino);

}
