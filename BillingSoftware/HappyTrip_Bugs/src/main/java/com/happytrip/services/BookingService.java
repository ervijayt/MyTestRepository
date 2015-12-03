package com.happytrip.services;

import java.util.List;

import com.happytrip.model.FlightBooking;

public interface BookingService {
	/**
	 * Return a list of all the ticket bookings made by the user
	 * @param userName
	 * @return
	 */
	List<FlightBooking> findAllFlightBookingsForUser(String userName);
}
