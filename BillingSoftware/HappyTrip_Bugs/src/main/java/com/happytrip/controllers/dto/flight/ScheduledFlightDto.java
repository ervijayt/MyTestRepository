package com.happytrip.controllers.dto.flight;

import java.util.Date;
import java.util.Set;

import com.happytrip.model.FlightBooking;
import com.happytrip.model.SeatAvailability;

public class ScheduledFlightDto extends FlightRouteDto{

	private Set<FlightBooking> bookings;
	
	private Date scheduledFlightDate;
	
	private Set<SeatAvailability> availability;

	public Set<FlightBooking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<FlightBooking> bookings) {
		this.bookings = bookings;
	}

	public Date getScheduledFlightDate() {
		return scheduledFlightDate;
	}

	public void setScheduledFlightDate(Date scheduledFlightDate) {
		this.scheduledFlightDate = scheduledFlightDate;
	}

	public Set<SeatAvailability> getAvailability() {
		return availability;
	}

	public void setAvailability(Set<SeatAvailability> availability) {
		this.availability = availability;
	}
}
