package br.com.smartclinmed.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.smartclinmed.web.domain.FileUpload;
import br.com.smartclinmed.web.domain.software.Inquilino;



@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Integer> {
	
	@Transactional(readOnly = true)
	FileUpload findByIdAndInquilino(Integer id, Inquilino inquilino);

}