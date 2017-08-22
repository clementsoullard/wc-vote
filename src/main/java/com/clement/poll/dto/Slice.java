package com.clement.poll.dto;

/**
 * Representation of a pie chart.
 * 
 * @author Clement_Soullard
 *
 */
public class Slice {
	private String name;

	private Float y;

	
	
	public Slice(String name, Float y) {
		super();
		this.name = name;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

}
