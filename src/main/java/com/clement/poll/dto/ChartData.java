package com.clement.poll.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartData {

	private List<String> categories = new ArrayList<>();

	private List<String> series = new ArrayList<>();

	public ChartData() {
		categories.addAll(Arrays.asList(new String[] { "Yes", "No" }));
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}

}
