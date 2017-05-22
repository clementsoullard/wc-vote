package com.clement.eventtracker.service.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

	Logger log = org.slf4j.LoggerFactory.getLogger(FileSystemStorageService.class);
	/** Root location were files are stored */
	private final Path rootLocation;

	/** Root location were temporary files for upload are stored */
	private final Path tempLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getRootLocation());
		this.tempLocation = Paths.get(properties.getTempLocation());
	}

	@Override
	public void store(MultipartFile file, String filename) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			IOUtils.copy(file.getInputStream(), new FileOutputStream(this.rootLocation + "/" + filename));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	/**
	 * Store a file in a temporary space.
	 * 
	 * @param file
	 * @param filename
	 */
	@Override
	public void storeTemp(MultipartFile file, String filename) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			IOUtils.copy(file.getInputStream(), new FileOutputStream(this.tempLocation + "/" + filename));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	/**
	 * Used to read input stream
	 */
	@Override
	public InputStream getInputStream(String id) throws IOException {
		return new FileInputStream(rootLocation + "/" + id);

	}

	/**
	 * Used to read temporary input stream
	 */
	@Override
	public InputStream getTempInputStream(String id) throws IOException {
		return new FileInputStream(tempLocation + "/" + id);

	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAllTemp() {
		FileSystemUtils.deleteRecursively(tempLocation.toFile());
	}

	@PostConstruct
	@Override
	public void init() {
		try {
			if (!Files.exists(rootLocation)) {
				Files.createDirectory(rootLocation);
			}
			if (!Files.exists(tempLocation)) {
				Files.createDirectory(tempLocation);
			}
		} catch (IOException e) {
			log.error("Erreur dans l'init du filesytem " + e);
		}
	}

	@Override
	public String moveTempToDefinitive(String tmpImgId) throws IOException {
		InputStream is = getTempInputStream(tmpImgId);
		IOUtils.copy(is, new FileOutputStream(rootLocation + File.separator + tmpImgId));
		return tmpImgId;
	}

}
