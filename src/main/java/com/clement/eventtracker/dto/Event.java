package com.clement.eventtracker.dto;

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

	private Boolean active = true;

	private Date date;

	private List<Participation> participations;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public void addParticipation(Participation participation) {
		if (participations == null) {
			participations = new ArrayList<Participation>();
		}
		participations.add(participation);
	}

}
