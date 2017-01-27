package com.clement.eventtracker.dto;

import org.springframework.data.annotation.Id;

public class BillBinary {

	@Id
	private String id;

	/**
	 * The debit entering in cashier
	 */
	private byte[] image;

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
