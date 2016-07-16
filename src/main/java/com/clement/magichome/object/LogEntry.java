package com.clement.magichome.object;

import java.util.Date;

import org.bson.Document;

public class LogEntry {

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

	public Document getDocument() {
		Document document = new Document("logEntry", new Document().append("metricName", metricName)
				.append("channel", channel).append("minutes", minutes).append("from", fromDate).append("to", toDate));
		return document;
	}
}
