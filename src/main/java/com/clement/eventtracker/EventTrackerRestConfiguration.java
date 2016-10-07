package com.clement.eventtracker;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.clement.eventtracker.service.storage.StorageProperties;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class EventTrackerRestConfiguration extends RepositoryRestMvcConfiguration {

	private static final String MY_BASE_URI_URI = "/ws";

	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setBasePath(MY_BASE_URI_URI);
	}
}