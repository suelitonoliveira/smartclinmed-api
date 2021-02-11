package br.com.smartclinmed.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.smartclinmed.web.domain.UploadedFile;
import br.com.smartclinmed.web.services.FileUploadResponse;
import br.com.smartclinmed.web.services.FileUploadService;



@RestController
@RequestMapping("inquilino/foto")
public class FileUploadResource {

	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping("/upload/local")
	public void uploadLocal(@RequestParam("file") MultipartFile multipartFile) {
		fileUploadService.uploadToLocal(multipartFile);
	}

	@PostMapping("/upload/db")
	public FileUploadResponse uploadDb(@RequestParam("file") MultipartFile multipartFile) {
		UploadedFile uploadedFile = fileUploadService.uploadToDb(multipartFile);
		FileUploadResponse response = new FileUploadResponse();
		if (uploadedFile != null) {
			String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("inquilino/foto/download/")
					.path(uploadedFile.getFileid()).toUriString();
			response.setDownloadUri(downloadUri);
			response.setFileId(uploadedFile.getFileid());
			response.setFileType(uploadedFile.getFileType());
			response.setUploadStatus(true);
			response.setMessage("File Uploaded SuccessFully!");
			return response;

		}
		response.setMessage("Ops, something went wrong please re-upload.");
		return response;
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
		UploadedFile uploadedFileToRet = fileUploadService.downloadFile(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadedFileToRet.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "anexo; filename= " + uploadedFileToRet.getFileName())
				.body(new ByteArrayResource(uploadedFileToRet.getFileData()));
	}

}
