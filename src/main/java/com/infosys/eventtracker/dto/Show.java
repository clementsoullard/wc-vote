package com.infosys.eventtracker.dto;

import org.springframework.data.annotation.Id;

public class Show {

	public String getIdr() {
		return id;
	}

	public void setIdr(String id) {
		this.id = id;
	}

	@Id
	private String id;

	private String showName;

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

}
