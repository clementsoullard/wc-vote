package com.clement.poll.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clement.poll.service.VoteDaoImpl;
import com.clement.poll.service.storage.StorageFileNotFoundException;
import com.clement.poll.service.storage.StorageProperties;
import com.clement.poll.service.storage.StorageService;

@RestController
public class FileUploadController {

	static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);

	private final StorageService storageService;

	@Autowired
	StorageProperties storageProperties;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/upload")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		int rnd = (int) (Math.random() * Integer.MAX_VALUE);
		storageService.storeTemp(file, Integer.toString(rnd));
		LOG.debug("Post file");
		return Integer.toString(rnd);
	}

	/**
	 * Serve an image in its permanent representation.
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/ws-img/{id}")
	public void download(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
		InputStream is = storageService.getInputStream(id);
		IOUtils.copy(is, response.getOutputStream());
	}

	/**
	 * Serve an image in its temporary representation.
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/ws-img-tmp/{id}")
	public void downloadTemp(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
		InputStream is = storageService.getTempInputStream(id);
		IOUtils.copy(is, response.getOutputStream());

	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
