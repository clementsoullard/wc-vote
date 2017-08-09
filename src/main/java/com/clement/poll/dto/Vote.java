package com.clement.poll.dto;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.Id;

/**
 * Represent a vote that is casted.
 * 
 * @author Clement_Soullard
 *
 */
public class Vote {

	public String getIdr() {
		return id;
	}

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private boolean answer;

	/**
	 * The poll id to which this vote related to.
	 */
	private String pollId;

	/**
	 * Tracer used to identify if the submitssion is issued by the same person
	 */
	private String tracer;
	/**
	 * The date when the vote has acutally been casted.
	 */
	private Date voteDate;

	public Vote(String firstName, String lastName, String email, String tracer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.tracer = tracer;
	}

	public Vote() {
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

	public String getTracer() {
		return tracer;
	}

	public void setTracer(String tracer) {
		this.tracer = tracer;
	}

	public void setIdr(String idr) {
		id = idr;
	}

	@Override
	public String toString() {
		return "" + firstName + ":" + answer;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	public Date getVoteDate() {
		return voteDate;
	}

	public void setVoteDate(Date voteDate) {
		this.voteDate = voteDate;
	}

}
