package com.happytrip.dao;

import java.util.List;

import com.happytrip.model.Flight;
import com.happytrip.model.FlightCapacity;

public interface FlightDao {
	void save(Flight flight);
	Flight findByFlightName(String flightName);
	Flight findByFlightId(long flightId);
	List<Flight> getAllFlights();
	List<FlightCapacity> getCapacitiesForFlightId(long flightId);
}
