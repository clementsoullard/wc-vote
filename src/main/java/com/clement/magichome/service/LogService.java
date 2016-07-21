package com.clement.magichome.service;

import java.util.Date;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.clement.magichome.object.LogEntry;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class LogService {

	static final Logger LOG = LoggerFactory.getLogger(LogRepositoryImpl.class);

	MongoClient mongoClient;

	public MongoClient mongoClient() throws Exception {
		if (mongoClient == null) {
			mongoClient = new MongoClient("192.168.1.20", 27017);
		}
		return mongoClient;
	}

	public MongoDatabase mongoDatabase() throws Exception {
		return mongoClient().getDatabase("test");
	}

	// private MongoCollection<Document> mc;

	public void insertlogEntry(Date from, Date to, Integer channel, Float minutes) {
		try {
			LogEntry logEntry = new LogEntry();
			logEntry.setMinutes(minutes);
			logEntry.setMetricName("TV");
			logEntry.setChannel(channel);
			logEntry.setFromDate(from);
			logEntry.setToDate(to);
			Long count = mongoDatabase().getCollection("log").count();
			// insertOne(logEntry.getDocument());
			LOG.debug("Inséré sans broncher " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
