package br.com.smartclinmed.web.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Iterator;
import java.util.Optional;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

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
	/*
	 * BufferedImage image = ImageIO.read(new File(baseFolderPath));
	 * ImageIO.write(image, "webp", new File("output.webp"));
	 */

	public void uploadImg(MultipartFile file, String uploadFolderPath, String uploadFileName) {

		try {
			File diretorio = new File(baseFolderPath + uploadFolderPath);
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			byte[] data = file.getBytes();
			// convert byte[] to a bufferedImage
			InputStream is = new ByteArrayInputStream(data);
			BufferedImage image = ImageIO.read(is);
			File compressedImageFile = new File("/var/www/html/images/compressed_image.jpg");
			OutputStream os = new FileOutputStream(compressedImageFile);
			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			ImageWriter writer = (ImageWriter) writers.next();

			ImageOutputStream ios = ImageIO.createImageOutputStream(os);
			writer.setOutput(ios);
			ImageWriteParam param = writer.getDefaultWriteParam();

			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.01f); // Change the quality value you prefer
			writer.write(null, new IIOImage(image, null, null), param);
			os.close();
			ios.close();
			writer.dispose();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void textImg(BufferedImage bufferedImage) throws IOException {

		String key = "Lucas Caresia";
		//BufferedImage bufferedImage = ImageIO.read(new File("recibo.png"));
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial Black", Font.PLAIN, 15));
		graphics.drawString(key, 420, 560);
		ImageIO.write(bufferedImage, "png", new File("text.jpg"));
		//System.out.println("Image Created");

	}

}
