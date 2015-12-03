package com.happytrip.model;

import java.io.Serializable;

/**
 * The persistent class for the classes database table.
 * 
 */
public class FlightClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private long classId;

	private String classType;

	public FlightClass() {
    }

	public FlightClass(long classid) {
		this.classId = classid;
    }

	public long getClassId() {
		return this.classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getClassType() {
		return this.classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
}