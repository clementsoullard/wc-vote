package com.clement.eventtracker.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
/**
 * This class is representing on event.
 * @author Clement_Soullard
 *
 */
public class Event {

	public String getIdr() {
		return id;
	}

	public void setIdr(String id) {
		this.id = id;
	}

	
	@Id
	private String id;

	private String name;

	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
