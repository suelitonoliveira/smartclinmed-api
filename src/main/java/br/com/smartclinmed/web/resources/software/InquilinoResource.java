package br.com.smartclinmed.web.resources.software;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.dto.software.InquilinoDTO;
import br.com.smartclinmed.web.dto.software.InquilinoNewDTO;
import br.com.smartclinmed.web.services.FileUploadService;
import br.com.smartclinmed.web.services.software.InquilinoService;



@RestController
@RequestMapping(value = "/inquilinos")
public class InquilinoResource {
	
    @Autowired
    private InquilinoService service;
    
    @Autowired
	private FileUploadService fileUploadService;

    @PreAuthorize("hasAnyRole('Inquilino_List')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Inquilino obj = service.find(id);
        return ResponseEntity.ok(obj);
    }
    
    @PreAuthorize("hasAnyRole('Inquilino_List')")
     @RequestMapping(method = RequestMethod.GET)
    public List<Inquilino> List() {
        return service.findAll();
    }
    
    @PreAuthorize("hasAnyRole('Inquilino_List')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Inquilino>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Inquilino> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
    
    @PreAuthorize("hasAnyRole('Inquilino_Insert')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Inquilino> insert(@Valid @RequestBody InquilinoNewDTO objDto) {
    	Inquilino obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
	@PreAuthorize("hasAnyRole('Inquilino_Update')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody InquilinoDTO objDto, @PathVariable Integer  id) {
		Inquilino obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('Inquilino_Delete')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer  id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
	@RequestMapping(value = "/image/{id}", method = RequestMethod.POST)
	public void uploadLocal(@PathVariable Integer id, @RequestParam("file") MultipartFile multipartFile) {
		Inquilino obj = service.find(id);
		String Path = id.toString() + "/";
		String Name = "Logo.jpg";
		fileUploadService.uploadToFile(multipartFile, Path, Name);
		obj.setImagem("http://localhost:8080" + Path + Name);
		obj = service.update(obj);
	}
	
	/*
	 * @RequestMapping(value = "/foto/{id}", method = RequestMethod.POST) public
	 * void uploadImage(@PathVariable Integer id, @RequestParam("file")
	 * MultipartFile multipartFile) { Inquilino obj = service.find(id); String Path
	 * = id.toString() + "/"; String Name = "Logo.webp";
	 * fileUploadService.uploadToImage(multipartFile, Path, Name);
	 * obj.setImagem("http://localhost:8080" + Path + Name); obj =
	 * service.update(obj); }
	 */
}
