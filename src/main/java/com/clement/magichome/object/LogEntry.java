package com.clement.magichome.object;

import java.util.Date;

import org.bson.Document;
import org.springframework.data.annotation.Id;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class LogEntry {

	@Id private String id;

	private String metricName;

	private Integer channel;

	private Float minutes;

	private Date fromDate;

	private Date toDate;

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Float getMinutes() {
		return minutes;
	}

	public void setMinutes(Float minutes) {
		this.minutes = minutes;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public DBObject getDocument() {
		DBObject dbObject=new BasicDBObject();
		dbObject.put("metricName", metricName);
		dbObject.put("channel", channel);
		dbObject.put("minutes", minutes);
		dbObject.put("from", fromDate);
		dbObject.put("to", toDate);
		return dbObject;
	}
}
