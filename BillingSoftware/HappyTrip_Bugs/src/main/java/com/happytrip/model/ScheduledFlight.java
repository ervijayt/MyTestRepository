package com.happytrip.model;

import java.util.Date;
import java.util.Set;
public class ScheduledFlight extends FlightRoute {
	
	private static final long serialVersionUID = 1L;

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
		return this.scheduledFlightDate;
	}
	public void setScheduledFlightDate(Date scheduledFlightDate) {
		this.scheduledFlightDate = (Date)scheduledFlightDate.clone();
	}
	public Set<SeatAvailability> getAvailability() {
		return availability;
	}
	public void setAvailability(Set<SeatAvailability> availability) {
		this.availability = availability;
	}

	
}
