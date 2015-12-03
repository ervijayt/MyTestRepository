package com.happytrip.controllers.dto.flight;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

import com.happytrip.model.Flight;
import com.happytrip.model.FlightRouteCost;
import com.happytrip.model.Route;

public class FlightRouteDto {
	private InputStream stream = new ByteArrayInputStream(new byte[10]);

	private long flightRouteId;

	private String arrivalTime;

	private String departureTime;

	private int distanceInKms;

	private int durationInMins;

	private String flightNumber;
	
	private Set<FlightRouteCost> flightRouteCosts;

	private Route route;

	private Flight flight;

	public long getFlightRouteId() {
		return flightRouteId;
	}

	public void setFlightRouteId(long flightRouteId) {
		this.flightRouteId = flightRouteId;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public int getDistanceInKms() {
		return distanceInKms;
	}

	public void setDistanceInKms(int distanceInKms) {
		this.distanceInKms = distanceInKms;
	}

	public int getDurationInMins() {
		return durationInMins;
	}

	public void setDurationInMins(int durationInMins) {
		this.durationInMins = durationInMins;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Set<FlightRouteCost> getFlightRouteCosts() {
		return flightRouteCosts;
	}

	public void setFlightRouteCosts(Set<FlightRouteCost> flightRouteCosts) {
		this.flightRouteCosts = flightRouteCosts;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
