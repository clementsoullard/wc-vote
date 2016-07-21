package com.clement.magichome;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.clement.magichome.service.LogRepositoryImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Configuration
public class AppConfig {

	@Autowired
	PropertyManager propertyManager;

	static final Logger LOG = LoggerFactory.getLogger(LogRepositoryImpl.class);

	MongoClient mongoClient;

	public @Bean MongoClient mongoClient() throws Exception {
		if (mongoClient == null) {
			mongoClient = new MongoClient("192.168.1.20", 27017);
		}
		return mongoClient;
	}

	public @Bean MongoDatabase mongoDatabase() throws Exception {
		return mongoClient().getDatabase(propertyManager.getDatabaseName());
	}

	public @Bean MongoTemplate mongoTemplate() {
		try {
			MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), propertyManager.getDatabaseName());
			LOG.info("mongoTemplate " + mongoTemplate);
			return mongoTemplate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
