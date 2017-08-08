package com.clement.poll.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * interface for managing storage
 * 
 * @author Clement_Soullard
 *
 */
public interface StorageService {

	void init();

	void store(MultipartFile file, String filename);

	void storeTemp(MultipartFile file, String filename);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAllTemp();

	InputStream getInputStream(String id) throws IOException;

	InputStream getTempInputStream(String id) throws IOException;

	String moveTempToDefinitive(String tmpImgId) throws IOException;

}
