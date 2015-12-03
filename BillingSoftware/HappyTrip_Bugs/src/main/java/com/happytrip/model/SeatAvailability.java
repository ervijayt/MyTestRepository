package com.happytrip.model;

import java.io.Serializable;

public class SeatAvailability implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private ScheduledFlight scheduledFlight;
	
	private FlightClass flightClass;
	
	private int availableSeats;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ScheduledFlight getScheduledFlight() {
		return scheduledFlight;
	}

	public void setScheduledFlight(ScheduledFlight scheduledFlight) {
		this.scheduledFlight = scheduledFlight;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
}
