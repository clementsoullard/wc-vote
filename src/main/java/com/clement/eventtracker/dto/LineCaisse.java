package com.clement.eventtracker.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class LineCaisse {

	public void setIdr(String id) {
		this.id = id;
	}

	@Id
	private String id;

	private String label;

	private String imageId;
	/**
	 * The debit entering in cashier
	 */
	private Float debit;
	/**
	 * The credit entering in cashier
	 */
	private Float credit;

	/**
	 * The date of the purchase
	 */
	private Date dateOfPurchase;

	/**
	 * The date of submission
	 */
	private Date dateOfSumission;

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

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date date) {
		this.dateOfPurchase = date;
	}

	public String getIdr() {
		return id;
	}

	public Date getDateOfSumission() {
		return dateOfSumission;
	}

	public void setDateOfSumission(Date dateOfSumission) {
		this.dateOfSumission = dateOfSumission;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

}
