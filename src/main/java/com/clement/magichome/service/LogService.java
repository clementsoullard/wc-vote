package com.clement.magichome.service;

import org.bson.codecs.Codec;
import org.springframework.stereotype.Repository;

import com.clement.magichome.object.LogEntry;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class LogService {
	MongoClient mongoClient = new MongoClient("192.168.1.20", 27017);;

	// public MongoClient getMongoClient() {
	// if (mongoClient == null) {
	// mongoClient = new MongoClient("192.168.1.20",27017);
	// }
	// return mongoClient;
	// }

	public void insertSomtething() {
		MongoDatabase md = mongoClient.getDatabase("test");
		MongoCollection mc = md.getCollection("log");
		LogEntry logEntry = new LogEntry();
		logEntry.setMetricValue(22);
		logEntry.setMetricName("TV");
		mc.insertOne(logEntry.getDocument());
	}
}
