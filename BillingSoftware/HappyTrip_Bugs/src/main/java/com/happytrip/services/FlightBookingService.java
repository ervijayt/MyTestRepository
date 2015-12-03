package com.happytrip.services;

import java.util.Date;
import java.util.List;

import com.happytrip.model.City;
import com.happytrip.model.FlightBooking;
import com.happytrip.model.FlightClass;
import com.happytrip.model.ScheduledFlight;

public interface FlightBookingService {

	/**
	 * Return a list of all the cities that 
	 * have flight originating from.
	 * @param cityName The name of the city or part there of
	 * @return
	 */
	List<City> findAllFromCities(String cityName);
	
	/**
	 * Returns a list of cities to which there is a 
	 * flight from the supplied city
	 * @param cityName
	 * @return list of city objects
	 */
	List<City> findAllCitiesFlownFrom(String cityName);
	
	/**
	 * Return all the scheduled flights that match the search criteria
	 * @param fromCity
	 * @param toCity
	 * @param classOfFlight
	 * @param paxNo
	 * @param departureDate
	 * @return List of scheduled flights
	 */
	List<ScheduledFlight> searchFlights(String fromCity, String toCity, FlightClass classOfFlight, int paxNo, Date departureDate) 
			;
	
	/**
	 * This method will book the flight
	 * Book a ticket
	 * @param flightBooking
	 */
	void bookFlight(FlightBooking flightBooking);
	
	/**
	 * This method will search a scheduled flight by its id
	 * @param id
	 */
	ScheduledFlight searchScheduledFlightById(long id);
}
