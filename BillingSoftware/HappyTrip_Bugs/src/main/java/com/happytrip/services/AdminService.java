package com.happytrip.services;

import java.util.List;

import com.happytrip.controllers.dto.flight.FlightScheduleDto;
import com.happytrip.model.*;

public interface AdminService {
	void addFlight(Flight flight, String airlineCode);
	boolean isDuplicateFlight(Flight flight);
	void addRoute(Route route);
	boolean isDuplicateRoute(String fromCity, String toCity);
	void addAirline(Airline airline);
	boolean isDuplicateAirline(Airline airline);
	List<Airline> getAllAirlines();
	FlightClass getClassForName(String className);
	Airline getAirlineByCode(String code);
	List<City> getAllCities();
	City getCityByName(String name);
	List<Flight> getAllFlights();
	List<Route> getAllRoutes();
	List<FlightClass> getAllFlightClasses();
	void addFlightSchedule(ScheduledFlight scheduledFlight);
	Flight getFlightById(long flightId);
	List<FlightCapacity> getCapacitiesForFlightId(long flightId);
	Route getRouteByCityNames(String fromCity, String toCity);
	void setScheduleFlightDetails(FlightScheduleDto flightScheduleDto,
			ScheduledFlight scheduledFlight, List<FlightCapacity> capacities);
}
