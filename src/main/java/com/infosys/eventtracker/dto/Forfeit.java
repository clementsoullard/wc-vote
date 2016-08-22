package com.infosys.eventtracker.dto;

import org.springframework.data.annotation.Id;

public class Forfeit {

	public String getIdr() {
		return id;
	}

	public void setIdr(String id) {
		this.id = id;
	}

	@Id
	private String id;

	private String forfeit;

	public String getForfeit() {
		return forfeit;
	}

	public void setForfeit(String forfeit) {
		this.forfeit = forfeit;
	}

}
