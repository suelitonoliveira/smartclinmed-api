package br.com.smartclinmed.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.smartclinmed.web.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	/*
	 * @Transactional(readOnly = true)
	 * 
	 * @Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome"
	 * ) public List<Cidade> finCidades(@Param("estadoId") Long estado_id);
	 */
	
	@Transactional(readOnly = true)
	public List<Cidade> findAllByOrderByNome();

}
