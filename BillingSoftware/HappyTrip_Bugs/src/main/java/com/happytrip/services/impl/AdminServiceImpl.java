package com.happytrip.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.happytrip.controllers.dto.flight.FlightScheduleDto;
import com.happytrip.dao.*;
import com.happytrip.dao.jdbc.JdbcAirlineDao;
import com.happytrip.dao.jdbc.JdbcCityDao;
import com.happytrip.dao.jdbc.JdbcFlightDao;
import com.happytrip.dao.jdbc.JdbcFlightRouteDao;
import com.happytrip.dao.jdbc.JdbcGenericDao;
import com.happytrip.dao.jdbc.JdbcLookupDao;
import com.happytrip.dao.jdbc.JdbcReportDao;
import com.happytrip.dao.jdbc.JdbcRouteDao;
import com.happytrip.dao.report.ReportDao;
import com.happytrip.model.*;
import com.happytrip.model.reports.AirlineReport;
import com.happytrip.model.reports.RouteReport;
import com.happytrip.model.reports.ScheduledFlightReport;
import com.happytrip.services.AdminService;
import com.happytrip.util.transformer.AirlineModelTransformer;
import com.happytrip.util.transformer.RouteModelTransformer;
import com.happytrip.util.transformer.ScheduledFlightModelTransformer;

public class AdminServiceImpl implements AdminService {

	private AirlineDao airlineDao;

	private FlightDao flightDao;

	private LookupDao lookupDao;

	private CityDao cityDao;

	private RouteDao routeDao;

	private FlightRouteDao flightRouteDao;

	private ReportDao reportDao;

	private GenericDao genericDao;
	public AdminServiceImpl() {
		lookupDao = new JdbcLookupDao();
		cityDao = new JdbcCityDao();
		airlineDao = new JdbcAirlineDao();
		flightDao = new JdbcFlightDao();
		cityDao = new JdbcCityDao();
		routeDao = new JdbcRouteDao();
		flightRouteDao = new JdbcFlightRouteDao();
		reportDao = new JdbcReportDao();
		genericDao = new JdbcGenericDao();
	}

	private boolean checkGenericAdmin(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		// get logged in username
		String name = auth.getName();
		return name.equalsIgnoreCase("happyadmin");
	}
	
	@Override
	public List<FlightClass> getAllFlightClasses() {
		if(checkGenericAdmin()){
			return genericDao.findAllFlightClasses();
		} else {
			return lookupDao.findAllFlightClasses();
		}
		
	}
	@Override
	public void addFlight(Flight flight, String airlineCode) {
		Airline airline = airlineDao.findByAirlineCode(airlineCode);
		flight.setAirline(airline);
		flightDao.save(flight);
	}

	@Override
	public boolean isDuplicateFlight(Flight flight) {
		Flight f = flightDao.findByFlightName(flight.getFlightName());
		return null != f;
	}

	@Override
	public void addRoute(Route route) {
		if(checkGenericAdmin()){
			genericDao.saveRoute(route);
		} else {
			routeDao.save(route);
		}
		RouteReport routeReport = RouteModelTransformer
				.transformToRouteReport(route);
		reportDao.storeRouteReport(routeReport);

	}

	@Override
	public boolean isDuplicateRoute(String fromCity, String toCity) {
		Route r = routeDao.findByCityNames(fromCity, toCity);
		return null != r;
	}

	@Override
	public void addAirline(Airline airline) {
		if(checkGenericAdmin()){
			genericDao.saveAirline(airline);
		} else {
			airlineDao.save(airline);
		}
		AirlineReport airlineReport = AirlineModelTransformer
				.transformToAirlineReport(airline);
		reportDao.storeAirlinesReport(airlineReport);
	}

	@Override
	public boolean isDuplicateAirline(Airline airline) {
		Airline air = airlineDao.findByAirlineCode(airline.getAirlineCode());
		return null != air;
	}

	@Override
	public List<Airline> getAllAirlines() {
		if(checkGenericAdmin()){
			return genericDao.getAllAirlines();
		} else {
			return airlineDao.findAll();
		}

	}

	@Override
	public FlightClass getClassForName(String className) {
		return lookupDao.findForClassName(className);
	}

	@Override
	public Airline getAirlineByCode(String code) {
		return airlineDao.findByAirlineCode(code);
	}

	@Override
	public List<City> getAllCities() {
		if(checkGenericAdmin()){
			return genericDao.getAllCities();
		} else {
			return cityDao.getAllCities();
		}

	}

	@Override
	public City getCityByName(String name) {
		return cityDao.findCityByName(name);
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightDao.getAllFlights();
	}

	@Override
	public List<Route> getAllRoutes() {
		return routeDao.getAllRoutes();
	}


	@Override
	public void addFlightSchedule(ScheduledFlight scheduledFlight) {

		flightRouteDao.saveScheduledFlight(scheduledFlight);
		ScheduledFlightReport scheduleFlightReport = ScheduledFlightModelTransformer
				.transformToScheduleReport(scheduledFlight);
		reportDao.storeScheduledFlighReport(scheduleFlightReport);
	}

	@Override
	public Flight getFlightById(long flightId) {
		return flightDao.findByFlightId(flightId);
	}

	@Override
	public List<FlightCapacity> getCapacitiesForFlightId(long flightId) {
		return flightDao.getCapacitiesForFlightId(flightId);
	}

	@Override
	public Route getRouteByCityNames(String fromCity, String toCity) {
		return routeDao.findByCityNames(fromCity, toCity);
	}
	
	@Override
	public void setScheduleFlightDetails(FlightScheduleDto flightScheduleDto,
			ScheduledFlight scheduledFlight, List<FlightCapacity> capacities) {
		Set<SeatAvailability> availabilitySet = new HashSet<SeatAvailability>();
		int capacitySize = capacities.size() - 1;
		// Loop through the capacities and create the flight capacity objects
		if (flightScheduleDto.getScheduledFlight().getDistanceInKms() > 1000) {
			capacitySize = capacities.size();
		}
		for (int i = 0; i <= capacitySize; i++) {
			FlightCapacity seats = capacities.get(i);
			SeatAvailability availability = new SeatAvailability();
			availability.setAvailableSeats(seats.getTotalSeats());
			availability.setFlightClass(seats.getFlightClass());
			availability.setScheduledFlight(scheduledFlight);
			availabilitySet.add(availability);
		}
		scheduledFlight.setAvailability(availabilitySet);
	}
	

}
