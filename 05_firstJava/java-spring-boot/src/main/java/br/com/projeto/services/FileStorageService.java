package br.com.projeto.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.projeto.config.FileStorageConfig;
import br.com.projeto.exceptions.FileStorageException;
import br.com.projeto.exceptions.MyFileNotFoundException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	public FileStorageService(FileStorageConfig config) {

		Path p = Paths.get(config.getUploadDir()).toAbsolutePath().normalize();

		this.fileStorageLocation = p;

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Não conseguiu criar pasta!!!");
		}

	}

	public String storeFile(MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			if (fileName.contains("..") || fileName.contains(" ")) {
				throw new FileStorageException("Arquivo inválido " + fileName);
			}
			
			Path target = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
			
			
			return fileName;
		} catch (Exception e) {
			throw new FileStorageException("Não conseguiu criar arquivo " + file.getName() + " !!!");
		}

	}

	public Resource loadFile(String filename) {
		try {
			Path target = this.fileStorageLocation.resolve(filename).normalize();
			Resource resource = new UrlResource(target.toUri());
			
			if(resource.exists()) return resource;
			else throw new MyFileNotFoundException("Arquivo não encontrado");
		} catch (Exception e) {
			throw new MyFileNotFoundException("Arquivo não encontrado "+filename, e);
		}
	}
}
