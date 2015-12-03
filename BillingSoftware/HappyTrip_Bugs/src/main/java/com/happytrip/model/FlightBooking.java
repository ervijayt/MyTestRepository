package com.happytrip.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the flightbookings database table.
 * 
 */
public class FlightBooking implements Serializable {
	private static final long serialVersionUID = 1L;

	private long bookingId;

	private float costPerTicket;

	private Date dateOfJourney;

	private int noOfSeats;

	//bi-directional many-to-one association to Class
	private FlightClass flightClass;

	//bi-directional many-to-one association to Flightroute
	private FlightRoute flightRoute;

	//bi-directional one-to-one association to Booking
	private Booking booking;

	//bi-directional many-to-one association to Passenger
	private Set<Passenger> passengers;

    public FlightBooking() {
    }

	public long getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public float getCostPerTicket() {
		return this.costPerTicket;
	}

	public void setCostPerTicket(float costPerTicket) {
		this.costPerTicket = costPerTicket;
	}

	public Date getDateOfJourney() {
		return (Date)this.dateOfJourney.clone();
	}

	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = (Date)dateOfJourney.clone();
	}

	public int getNoOfSeats() {
		return this.noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public FlightClass getFlightClass() {
		return this.flightClass;
	}

	public void setFlightClass(FlightClass classId) {
		this.flightClass = classId;
	}
	
	public FlightRoute getFlightRoute() {
		return this.flightRoute;
	}

	public void setFlightRoute(FlightRoute flightRoute) {
		this.flightRoute = flightRoute;
	}
	
	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	public Set<Passenger> getPassengers() {
		return this.passengers;
	}

	public void setPassengers(Set<Passenger> passengers) {
		this.passengers = passengers;
	}
	
}