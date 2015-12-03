package com.happytrip.model;

import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the flights database table.
 * 
 */
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	private long flightId;

	private String flightName;

	//bi-directional many-to-one association to Flightroute
	private Set<FlightRoute> flightRoutes;

	//bi-directional many-to-one association to Airline
	private Airline airline;
    
    private Set<FlightCapacity> capacity;

	//bi-directional many-to-many association to Route
	private Set<Route> routes;

    public Flight() {
    }

	public Flight(long long1) {
		this.flightId = long1;
	}

	public long getFlightId() {
		return this.flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return this.flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public Set<FlightRoute> getFlightRoutes() {
		return this.flightRoutes;
	}

	public void setFlightRoutes(Set<FlightRoute> flightRoutes) {
		this.flightRoutes = flightRoutes;
	}
	
	public Airline getAirline() {
		return this.airline;
	}

	public Set<FlightCapacity> getCapacity() {
		return capacity;
	}

	public void setCapacity(Set<FlightCapacity> capacity) {
		this.capacity = capacity;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	public Set<Route> getRoutes() {
		return this.routes;
	}

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}
	
	public boolean addFlightCapacity(FlightCapacity flightCapacity)
	{
		return this.capacity.add(flightCapacity);
	}
}