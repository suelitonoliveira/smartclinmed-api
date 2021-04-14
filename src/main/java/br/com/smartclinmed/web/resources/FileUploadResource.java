package br.com.smartclinmed.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.smartclinmed.web.domain.FileUpload;
import br.com.smartclinmed.web.services.FileUploadService;



@RestController
@RequestMapping("inquilino/foto")
public class FileUploadResource {


	@Autowired
	private FileUploadService service;

	@PreAuthorize("hasAnyRole('FileUpload_Upload')")
	@PostMapping("/upload/local")
	public void uploadLocal(@RequestParam("file") MultipartFile multipartFile, @RequestParam("path") String Path,
			@RequestParam("name") String Name) {
		service.uploadToFile(multipartFile, Path, Name);
	}

	// @PreAuthorize("hasAnyRole('FileUpload_Upload')")
	// @PostMapping("/upload/db")
	// public FileUploadResponse uploadDb(@RequestParam("file") MultipartFile
	// multipartFile) {
	// FileUpload uploadedFile = service.uploadToFile64(multipartFile);
	// FileUploadResponse response = new FileUploadResponse();
	// if (uploadedFile != null) {
	// String downloadUri =
	// ServletUriComponentsBuilder.fromCurrentContextPath().path("inquilino/foto/download/")
	// .path(uploadedFile.getId().toString()).toUriString();
	// response.setDownloadUri(downloadUri);
	// response.setFileId(uploadedFile.getId());
	// response.setFileType(uploadedFile.getFileType());
	// response.setUploadStatus(true);
	// response.setMessage("File Uploaded SuccessFully!");
	// return response;

	// }
	// response.setMessage("Ops, something went wrong please re-upload.");
	// return response;
	// }

	@PreAuthorize("hasAnyRole('FileUpload_Download')")
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Integer fileId) {
		FileUpload uploadedFileToRet = service.downloadFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadedFileToRet.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "anexo; filename= " + uploadedFileToRet.getFileName())
				.body(new ByteArrayResource(uploadedFileToRet.getFileData()));
	}

}
