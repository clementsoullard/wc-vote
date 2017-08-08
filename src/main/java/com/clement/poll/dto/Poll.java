package com.clement.poll.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * This class is representing on event.
 * 
 * @author Clement_Soullard
 *
 */
public class Poll {

	public String getIdr() {
		return id;
	}

	public void setIdr(String id) {
		this.id = id;
	}

	@Id
	private String id;
	/**
	 * Name of the event
	 */
	private String name;

	/**
	 * If the event is active
	 */
	private Boolean active = true;

	/**
	 * The description of the event
	 */
	private String description;

	/**
	 * The maximum date before registration of the event
	 */
	private Date dateMaxVote;

	/**
	 * The creation Date
	 */
	private Date creationDate;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getDateMaxVote() {
		return dateMaxVote;
	}

	public void setDateMaxVote(Date dateMaxVote) {
		this.dateMaxVote = dateMaxVote;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
