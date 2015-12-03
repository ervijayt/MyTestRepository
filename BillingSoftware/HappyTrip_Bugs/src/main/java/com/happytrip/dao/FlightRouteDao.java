package com.happytrip.dao;

import java.util.Date;
import java.util.List;

import com.happytrip.model.*;

public interface FlightRouteDao {

	List<City> findAllFromCities(String fromCity);
	List<City> findAllToCity(String fromCity);
	List<ScheduledFlight> searchFlights(String fromCity, String toCity, 
			Date dateOfTravel, FlightClass classOfFlight, int paxNo);
	void saveScheduledFlight(ScheduledFlight scheduledFlight);
	ScheduledFlight findDetailsById(long id, String classOfFlight);
	ScheduledFlight findScheduledFlightById(long id);
}
