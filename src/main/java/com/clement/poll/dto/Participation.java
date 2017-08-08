package com.clement.poll.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Participation {

	public String getIdr() {
		return id;
	}

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private String event;

	/**
	 * Tracer used to identify if the submitssion is issued by the same person
	 */
	private String tracer;

	private boolean vegetarian;

	private boolean employee;

	private boolean infamed;

	private boolean paid;

	private Date registrationDate;

	private String gender;

	private String age;
	/**
	 * This is to store the additional questions
	 */
	private HashMap<String, String> questions;

	public Participation(String firstName, String lastName, String email, boolean vegetarianOption, boolean isEmployee,
			boolean isChild, String tracer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.vegetarian = vegetarianOption;
		this.employee = isEmployee;
		this.isChild = isChild;
		this.tracer = tracer;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private boolean isChild;

	public Participation() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarianOption) {
		this.vegetarian = vegetarianOption;
	}

	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean isEmployee) {
		this.employee = isEmployee;
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public String getTracer() {
		return tracer;
	}

	public void setTracer(String tracer) {
		this.tracer = tracer;
	}

	public void setIdr(String idr) {
		id = idr;
	}

	public boolean isInfamed() {
		return infamed;
	}

	public void setInfamed(boolean infamed) {
		this.infamed = infamed;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "" + firstName + ":" + registrationDate;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean pay) {
		this.paid = pay;
	}

	public HashMap<String,String> getQuestions() {
		return questions;
	}

	public void setQuestions(HashMap<String,String> questions) {
		this.questions = questions;
	}

}
