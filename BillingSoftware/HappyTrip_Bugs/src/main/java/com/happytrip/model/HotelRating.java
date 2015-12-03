package com.happytrip.model;

import java.io.Serializable;
import java.math.BigInteger;


/**
 * The persistent class for the hotelratings database table.
 * 
 */
public class HotelRating implements Serializable {
	private static final long serialVersionUID = 1L;

	private long hotelId;

	private float rating;

	private BigInteger totalRatings;

	//bi-directional one-to-one association to Hotel
	private Hotel hotel;

    public HotelRating() {
    }

	public long getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public BigInteger getTotalRatings() {
		return this.totalRatings;
	}

	public void setTotalRatings(BigInteger totalRatings) {
		this.totalRatings = totalRatings;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
}