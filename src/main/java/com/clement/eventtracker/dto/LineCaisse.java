package com.clement.eventtracker.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class LineCaisse {

	public String getIdr() {
		return id;
	}

	public void setIdr(String id) {
		this.id = id;
	}

	@Id
	private String id;

	private String label;
	
	private Float debit;

	private Float credit;

	/**
	 * The date of the purchase
	 */
	private Date date;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Float getDebit() {
		return debit;
	}

	public void setDebit(Float debit) {
		this.debit = debit;
	}

	public Float getCredit() {
		return credit;
	}

	public void setCredit(Float credit) {
		this.credit = credit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
