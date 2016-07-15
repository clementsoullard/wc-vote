package com.clement.magichome.service;

import org.springframework.stereotype.Repository;

import com.clement.magichome.object.LogEntry;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

@Repository
public class LogService {

	private static MongoCollection mc;

	public static MongoCollection  getLogCollection() {
		if (mc == null) {
			MongoClient mongoClient = new MongoClient("192.168.1.20", 27017);
			mongoClient = new MongoClient("192.168.1.20", 27017);
			mc = mongoClient.getDatabase("test").getCollection("log");
		}
		return mc;
	}

	public void insertSomtething() {
		MongoCollection mc =getLogCollection(); 
		LogEntry logEntry = new LogEntry();
		logEntry.setMetricValue(22);
		logEntry.setMetricName("TV");
		mc.insertOne(logEntry.getDocument());
	}
}
