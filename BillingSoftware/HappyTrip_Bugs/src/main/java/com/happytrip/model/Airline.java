package com.happytrip.model;

import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the airlines database table.
 * 
 */

public class Airline implements Serializable {
	private static final long serialVersionUID = 1L;

	private long airlineId;


	private String airlineCode;


	private String airlineLogo;


	private String airlineName;

	//bi-directional many-to-one association to Flight

	private Set<Flight> flights;

    public Airline() {
    }

	public Airline(long long1) {
		this.airlineId = long1;
	}

	public long getAirlineId() {
		return this.airlineId;
	}

	public void setAirlineId(long airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineCode() {
		return this.airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineLogo() {
		return this.airlineLogo;
	}

	public void setAirlineLogo(String airlineLogo) {
		this.airlineLogo = airlineLogo;
	}

	public String getAirlineName() {
		return this.airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public Set<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}
	
}