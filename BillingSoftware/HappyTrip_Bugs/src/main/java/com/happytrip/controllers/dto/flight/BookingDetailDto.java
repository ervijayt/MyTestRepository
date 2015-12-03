package com.happytrip.controllers.dto.flight;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Set;

import com.happytrip.model.Booking;
import com.happytrip.model.FlightClass;
import com.happytrip.model.FlightRoute;
import com.happytrip.model.Passenger;

public class BookingDetailDto {
	private InputStream userStream = new ByteArrayInputStream(new byte[10]);

	private long bookingId;

	private float costPerTicket;

	private Date dateOfJourney;

	private int noOfSeats;

	private FlightClass flightClass;

	private FlightRoute flightRoute;

	private Booking booking;

	private Set<Passenger> passengers;

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public float getCostPerTicket() {
		return costPerTicket;
	}

	public void setCostPerTicket(float costPerTicket) {
		this.costPerTicket = costPerTicket;
	}

	public Date getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}

	public FlightRoute getFlightRoute() {
		return flightRoute;
	}

	public void setFlightRoute(FlightRoute flightRoute) {
		this.flightRoute = flightRoute;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Set<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<Passenger> passengers) {
		this.passengers = passengers;
	}

	public InputStream getUserStream() {
		return userStream;
	}

	public void setUserStream(InputStream userStream) {
		this.userStream = userStream;
	}
}
