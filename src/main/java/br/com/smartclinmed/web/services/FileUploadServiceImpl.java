package br.com.smartclinmed.web.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.smartclinmed.web.domain.UploadedFile;
import br.com.smartclinmed.web.repositories.FileUploadRepository;



@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	private String uploadFolderPath = "/Users/suporte/Desktop/upload/uploaded_";
	@Autowired
	private FileUploadRepository FileUploadRepository;
	
@Override
public void uploadToLocal(MultipartFile file) {
	
	try {
		byte[] data = file.getBytes();
		Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
		Files.write(path,data);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

@Override
public UploadedFile uploadToDb(MultipartFile file) {
	try {
		UploadedFile uploadedFile = new UploadedFile();
		uploadedFile.setFileData(file.getBytes());
		uploadedFile.setFileType(file.getContentType());
		uploadedFile.setFileName(file.getOriginalFilename());
		UploadedFile uploadedFileToRet = FileUploadRepository.save(uploadedFile);
		return uploadedFileToRet;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	
	
}

@Override
public UploadedFile downloadFile(String fileId) {
	UploadedFile uploadedFileToRet = FileUploadRepository.getOne(fileId);
	return uploadedFileToRet;
}

}
