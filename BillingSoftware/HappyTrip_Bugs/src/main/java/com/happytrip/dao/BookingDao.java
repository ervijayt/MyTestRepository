package com.happytrip.dao;

import java.util.List;

import com.happytrip.model.FlightBooking;

public interface BookingDao {
	List<FlightBooking> getAllFlightBookingsForUser(String userName);
}
