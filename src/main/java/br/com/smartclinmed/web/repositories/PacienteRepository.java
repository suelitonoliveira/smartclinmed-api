package br.com.smartclinmed.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.smartclinmed.web.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	

}
