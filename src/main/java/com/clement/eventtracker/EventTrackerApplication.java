package com.clement.eventtracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.clement.eventtracker.service.storage.StorageService;

/**
 * This is the main class that launch the spring application.
 * 
 * @author Clement_Soullard
 *
 */

@SpringBootApplication
public class EventTrackerApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(EventTrackerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
            storageService.deleteAll();
            storageService.init();
		};
	}

}