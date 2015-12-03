package com.happytrip.controllers.dto.flight;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

import com.happytrip.model.Flight;

public class AirlineDto {
	private InputStream userStream = new ByteArrayInputStream(new byte[10]);

	private long airlineId;

	private String airlineCode;

	private String airlineLogo;

	private String airlineName;

	private Set<Flight> flights;

	public long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(long airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineLogo() {
		return airlineLogo;
	}

	public void setAirlineLogo(String airlineLogo) {
		this.airlineLogo = airlineLogo;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public InputStream getUserStream() {
		return userStream;
	}

	public void setUserStream(InputStream userStream) {
		this.userStream = userStream;
	}
}
