package com.happytrip.dao;

import java.util.List;
import com.happytrip.model.*;

public interface GenericDao {
	List<City> getAllCities();
	List<Airline> getAllAirlines();
	List<FlightClass> findAllFlightClasses();
	void saveAirline(Airline airline);
	void saveRoute(Route route);
}
