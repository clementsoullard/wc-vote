package com.clement.magichome.object;

import java.util.Date;

public class BonPoint {
	private Integer point;
	private Integer pointConsumed;
	private Date date;
	private String rationale;

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getPointConsumed() {
		return pointConsumed;
	}

	public void setPointConsumed(Integer pointConsumed) {
		this.pointConsumed = pointConsumed;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public BonPoint(Integer point, Integer pointConsumed, Date date, String rationale) {
		super();
		this.point = point;
		this.pointConsumed = pointConsumed;
		this.date = date;
		this.rationale = rationale;
	}

	public BonPoint() {
	}

}
