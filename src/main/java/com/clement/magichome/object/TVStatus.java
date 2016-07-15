package com.clement.magichome.object;

public class TVStatus {
	private Boolean relayStatus;
	
	private TVData data;

	public TVData getData() {
		return data;
	}

	public void setData(TVData data) {
		this.data = data;
	}

	public Boolean getRelayStatus() {
		return relayStatus;
	}

	public void setRelayStatus(Boolean relayStatus) {
		this.relayStatus = relayStatus;
	}
}
