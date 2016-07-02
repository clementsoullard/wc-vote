package com.clement.magichome.object;

import org.bson.BsonDocument;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class LogEntry {
	private String metricName;

	private Integer metricValue;

	public Integer getMetricValue() {
		return metricValue;
	}

	public void setMetricValue(Integer metricValue) {
		this.metricValue = metricValue;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public Document getDocument() {
		Document document = new Document("logEntry",
				new Document().append("metricName", metricName).append("value", metricValue));
		return document;
	}
}
