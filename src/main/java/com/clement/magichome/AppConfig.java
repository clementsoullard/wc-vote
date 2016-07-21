package com.clement.magichome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.clement.magichome.service.LogRepositoryImpl;
import com.mongodb.MongoClient;

@Configuration
public class AppConfig {

	static final Logger LOG = LoggerFactory.getLogger(LogRepositoryImpl.class);

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient("192.168.1.20"), "test");
	}

	public @Bean MongoTemplate mongoTemplate() {
		try {
			MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
			LOG.info("mongoTemplate " + mongoTemplate);
			return mongoTemplate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
