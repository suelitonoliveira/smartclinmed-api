package br.com.smartclinmed.web.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.smartclinmed.web.domain.FileUpload;
import br.com.smartclinmed.web.domain.software.Inquilino;
import br.com.smartclinmed.web.repositories.FileUploadRepository;
import br.com.smartclinmed.web.repositories.software.InquilinoRepository;
import br.com.smartclinmed.web.security.UserSS;
import br.com.smartclinmed.web.services.acessos.UserService;
import br.com.smartclinmed.web.services.exceptions.AuthorizationException;

@Service
public class FileUploadService {

	private String baseFolderPath = "/var/www/html/images/";
	

	@Autowired
	private FileUploadRepository repo;

	@Autowired
	private InquilinoRepository inquilinoRepository;


	public void uploadToFile(MultipartFile file, String uploadFolderPath, String uploadFileName) {

		try {
			File diretorio = new File(baseFolderPath + uploadFolderPath);
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			byte[] data = file.getBytes();
			Path path = Paths.get(baseFolderPath + uploadFolderPath + uploadFileName);
			Files.write(path, data);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void uploadToFile64(MultipartFile file) {
		try {
			UserSS user = UserService.authenticated();
			if (user == null) {
				throw new AuthorizationException("Acesso Negado");
			}

			FileUpload uploadedFile = new FileUpload();
			uploadedFile.setInquilino(user.getInquilino());
			Optional<Inquilino> inq = inquilinoRepository.findById(user.getId());
			uploadedFile.setFileData(file.getBytes());
			uploadedFile.setFileType(file.getContentType());
			uploadedFile.setFileName(file.getOriginalFilename());
			repo.save(uploadedFile);
			inq.get().setImagem64(uploadedFile.toString());
			inquilinoRepository.save(inq.get());

			// return uploadedFileToRet;
		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}

	}

	public void uploadBase64Image(String base64Str, String uploadFolderPath, String uploadFileName) {
		byte[] decodedBytes = Base64.getDecoder().decode(base64Str.getBytes(StandardCharsets.UTF_8));
		try {
			File diretorio = new File(baseFolderPath + uploadFolderPath);
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			File file = new File(baseFolderPath + uploadFolderPath + uploadFileName);
			if (file.exists()) {
				file.delete();
			}
			FileUtils.writeByteArrayToFile(new File(baseFolderPath + uploadFolderPath + uploadFileName), decodedBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileUpload downloadFile(Integer id) {
		FileUpload uploadedFileToRet = repo.getOne(id);
		return uploadedFileToRet;
	}

	// public MultipartFile resizeImg(MultipartFile sourceImg, int size) throws
	// IOException {
	// BufferedImage img = ImageIO.read(sourceImg.getInputStream());
	// BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
	// BufferedImage.TYPE_INT_RGB);
	// jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
	// BufferedImage imgBuffer = Scalr.resize(jpgImage, Scalr.Method.ULTRA_QUALITY,
	// size);

	// BufferedImage bi = imgBuffer;
	// File outputfile = new File("saved.jpg");
	// ImageIO.write(bi, "jpg", outputfile);
	// MultipartFile multipartFile = new MockMultipartFile("file",
	// file.getName(), "text/plain", IOUtils.toByteArray(outputfile));

	// return (MultipartFile) outputfile;

	// }

}
