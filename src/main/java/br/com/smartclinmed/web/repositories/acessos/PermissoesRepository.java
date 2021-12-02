package br.com.smartclinmed.web.repositories.acessos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smartclinmed.web.domain.acessos.Permissao;

public interface PermissoesRepository extends JpaRepository<Permissao, Integer> {

}
