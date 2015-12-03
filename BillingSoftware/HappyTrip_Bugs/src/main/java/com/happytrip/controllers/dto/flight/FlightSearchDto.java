package com.happytrip.controllers.dto.flight;

import java.util.Date;

public class FlightSearchDto {

	private boolean returnJourney;
	private String fromCity;
	private String toCity;
	private Date departureDate;
	private Date returnDate;
	private int noOfAdults;
	private int noOfChildren;
	private int noOfInfants;
	private String flightClass;

	public boolean isReturnJourney() {
		return returnJourney;
	}

	public void setReturnJourney(boolean returnJourney) {
		this.returnJourney = returnJourney;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Date getDepartureDate() {
		return this.departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = (Date) departureDate;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = (Date) returnDate;
	}

	public int getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public int getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public int getNoOfInfants() {
		return noOfInfants;
	}

	public void setNoOfInfants(int noOfInfants) {
		this.noOfInfants = noOfInfants;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
}
