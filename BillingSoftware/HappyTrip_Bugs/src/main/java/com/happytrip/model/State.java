package com.happytrip.model;

import java.io.Serializable;

/**
 * The persistent class for the states database table.
 * 
 */
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	private long stateId;

	private String stateName;

	public State() {
    }

	public State(long long1) {
		this.stateId = long1;
	}

	public long getStateId() {
		return this.stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}