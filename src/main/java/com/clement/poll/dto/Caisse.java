package com.clement.poll.dto;

import java.util.List;

/**
 * 
 * @author Clement_Soullard
 *
 */
public class Caisse {

	public List<LineCaisse> getLineCaisses() {
		return lineCaisses;
	}

	public void setLineCaisses(List<LineCaisse> lineCaisses) {
		this.lineCaisses = lineCaisses;
	}

	public CaisseSummary getCaisseSummary() {
		return caisseSummary;
	}

	public void setCaisseSummary(CaisseSummary caisseSummary) {
		this.caisseSummary = caisseSummary;
	}

	private List<LineCaisse> lineCaisses;

	private CaisseSummary caisseSummary;

}
