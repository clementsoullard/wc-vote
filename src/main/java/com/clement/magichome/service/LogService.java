package com.clement.magichome.service;

import java.util.Date;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.clement.magichome.object.LogEntry;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

@Repository
public class LogService {

	private MongoCollection<Document> mc;

	public MongoCollection<Document> getLogCollection() {
		if (mc == null) {
			MongoClient mongoClient = new MongoClient("192.168.1.20", 27017);
			mongoClient = new MongoClient("192.168.1.20", 27017);
			mc = mongoClient.getDatabase("test").getCollection("log");
		}
		return mc;
	}

	public void insertlogEntry(Date from, Date to, Integer channel, Float minutes) {
		MongoCollection<Document> mc = getLogCollection();
		LogEntry logEntry = new LogEntry();
		logEntry.setMinutes(minutes);
		logEntry.setMetricName("TV");
		logEntry.setChannel(channel);
		logEntry.setFromDate(from);
		logEntry.setToDate(to);
		mc.insertOne(logEntry.getDocument());
	}
}
