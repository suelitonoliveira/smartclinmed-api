package br.com.smartclinmed.web.repositories.acessos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.smartclinmed.web.domain.acessos.Usuario;
import br.com.smartclinmed.web.domain.software.Inquilino;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Transactional(readOnly=true)
	Usuario findByEmail(String email);

	@Transactional(readOnly=true)
	Usuario findByEmailAndInquilino(String email, Inquilino inquilino);
	
	@Transactional(readOnly=true)
	Optional<Usuario> findByIdAndInquilino(Integer Id, Inquilino inquilino);
	
	@Transactional(readOnly=true)
	List<Usuario> findByInquilino(Inquilino inquilino);
	
	@Transactional(readOnly=true)
	Page<Usuario> findByInquilino(Inquilino inquilino, Pageable pageable);
	

}
