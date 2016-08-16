package com.infosys.eventtracker.dto;

public class Participation {

	private String firstName;

	private String lastName;

	private String email;

	private boolean vegetarianOption;

	private boolean isEmployee;

	private boolean isChild;

	public Participation(String firstName, String lastName, String email, boolean vegetarianOption, boolean isEmployee,
			boolean isChild) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.vegetarianOption = vegetarianOption;
		this.isEmployee = isEmployee;
		this.isChild = isChild;
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

	public boolean isVegetarianOption() {
		return vegetarianOption;
	}

	public void setVegetarianOption(boolean vegetarianOption) {
		this.vegetarianOption = vegetarianOption;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}



}
