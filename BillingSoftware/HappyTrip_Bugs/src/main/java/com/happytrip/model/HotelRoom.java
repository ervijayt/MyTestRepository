package com.happytrip.model;

import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the hotelrooms database table.
 * 
 */
public class HotelRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private float costPerDay;

	private int noOfRooms;

	private RoomType roomType;
	
	//bi-directional many-to-one association to Hotelbooking
	private Set<HotelBooking> hotelbookings;

    public HotelRoom() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public float getCostPerDay() {
		return this.costPerDay;
	}

	public void setCostPerDay(float costPerDay) {
		this.costPerDay = costPerDay;
	}

	public int getNoOfRooms() {
		return this.noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Set<HotelBooking> getHotelbookings() {
		return this.hotelbookings;
	}

	public void setHotelbookings(Set<HotelBooking> hotelbookings) {
		this.hotelbookings = hotelbookings;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
}