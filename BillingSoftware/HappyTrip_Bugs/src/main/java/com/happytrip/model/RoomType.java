package com.happytrip.model;

import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the roomtypes database table.
 * 
 */
public class RoomType implements Serializable {
	private static final long serialVersionUID = 1L;

	private long typeId;

	private String title;

	//bi-directional many-to-many association to Hotel
	private Set<Hotel> hotels;

    public RoomType() {
    }

	public long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Hotel> getHotels() {
		return this.hotels;
	}

	public void setHotels(Set<Hotel> hotels) {
		this.hotels = hotels;
	}
	
}