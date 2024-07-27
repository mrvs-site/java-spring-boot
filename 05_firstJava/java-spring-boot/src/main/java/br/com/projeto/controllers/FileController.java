package br.com.projeto.controllers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.projeto.data.vo.v1.UploadFileResponseVO;
import br.com.projeto.services.FileStorageService;
import ch.qos.logback.core.joran.conditional.ThenAction;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "File endpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

	
	private Logger logger = Logger.getLogger(FileController.class.getName());
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponseVO  uploadFile(@RequestParam("file") MultipartFile file) {
		
		logger.info("enviando arquivo para a pasta");
		
		var fileName = fileStorageService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/file/v1/downloadFile")
				.path(fileName)
				.toUriString();
		
		
		return new UploadFileResponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	@PostMapping("/uploadFiles")
	public List<UploadFileResponseVO>  uploadFiles(@RequestParam("files") MultipartFile[] files) {
		
		logger.info("enviando arquivo para a pasta");
		
		
		
		return Arrays.asList(files).stream().map(f -> uploadFile(f)).collect(Collectors.toList());
	}
	
	@GetMapping("/download/{filename:.+}")
	public ResponseEntity<Resource> download(@PathVariable String filename, HttpServletRequest httpServletRequest) {
		
		logger.info("download do arquivo para a pasta");
		
		
		Resource r = fileStorageService.loadFile(filename);
		String contentType = "";
		
		try {
			contentType = httpServletRequest.getServletContext().getMimeType(r.getFile().getAbsolutePath());
		} catch (Exception e) {

			logger.info("download error!!!");
		}
		
		if(contentType.isBlank()) contentType = "application/octet-stream";
		
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + r.getFilename() + "")
				.body(r);
	}
}
