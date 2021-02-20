package br.com.smartclinmed.web.repositories.acessos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smartclinmed.web.acessos.Permissao;

public interface PermissoesRepository extends JpaRepository<Permissao, Integer> {

}
