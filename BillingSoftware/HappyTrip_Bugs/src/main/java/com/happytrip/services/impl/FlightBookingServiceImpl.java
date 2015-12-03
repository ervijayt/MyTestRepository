package com.happytrip.services.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.happytrip.controllers.dto.flight.BookingDetailDto;
import com.happytrip.dao.BackupDao;
import com.happytrip.dao.CityDao;
import com.happytrip.dao.FlightBookingDao;
import com.happytrip.dao.FlightRouteDao;
import com.happytrip.dao.jdbc.JdbcBackupDao;
import com.happytrip.dao.jdbc.JdbcCityDao;
import com.happytrip.dao.jdbc.JdbcFlightBookingDao;
import com.happytrip.dao.jdbc.JdbcFlightRouteDao;
import com.happytrip.dao.jdbc.JdbcReportDao;
import com.happytrip.dao.report.ReportDao;
import com.happytrip.model.Backup;
import com.happytrip.model.City;
import com.happytrip.model.FlightBooking;
import com.happytrip.model.FlightClass;
import com.happytrip.model.ScheduledFlight;
import com.happytrip.model.reports.BookingReport;
import com.happytrip.services.FlightBookingService;
import com.happytrip.util.BeanFactory;
import com.happytrip.util.backup.FlightBookingBackup;
import com.happytrip.util.transformer.FlightBookingModelTransformer;

public class FlightBookingServiceImpl implements FlightBookingService {

	private FlightRouteDao flightRouteDao;
	
	private FlightBookingDao flightBookingDao;
	
	private BackupDao backupDao;

	private CityDao cityDao;

	private ReportDao reportDao;
	
	public FlightBookingServiceImpl() {
		flightBookingDao = new JdbcFlightBookingDao();
		flightRouteDao = new JdbcFlightRouteDao();
		backupDao = new JdbcBackupDao();
		reportDao = new JdbcReportDao();
		cityDao = new JdbcCityDao();
	}
	
	@Override
	public List<City> findAllFromCities(String cityName) {
		List<City> cities = flightRouteDao.findAllFromCities(cityName);
		return cities.isEmpty()?flightRouteDao.findAllToCity(cityName):cities;
	}

	@Override
	public List<City> findAllCitiesFlownFrom(String cityName) {
		// TODO Auto-generated method stub
		return flightRouteDao.findAllToCity(cityName);
	}

	@Override
	public List<ScheduledFlight> searchFlights(String fromCity, String toCity,
			FlightClass classOfFlight, int paxNo, Date departureDate) {
		// TODO Auto-generated method stub
		return flightRouteDao.searchFlights(fromCity, toCity, departureDate,
				classOfFlight, paxNo);
	}

	@Override
	public void bookFlight(FlightBooking flightBooking) {
		// TODO Auto-generated method stub
		if(flightBooking.getCostPerTicket()>5000){
			BookingDetailDto booking = FlightBookingModelTransformer.transform(flightBooking);
			try {
				byte[] data = FlightBookingBackup.backup(booking);
				Backup backup = new Backup();
				backup.setData(data);
				backup.setName("FlightBooking");
				backupDao.save(backup);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
			
		}
		flightBookingDao.save(flightBooking);
		BookingReport bookingReport = FlightBookingModelTransformer.transformToBookingReport(flightBooking);
		reportDao.storeBookingReport(bookingReport );
		
	}
	
	@Override
	public ScheduledFlight searchScheduledFlightById(long id){
		return flightRouteDao.findScheduledFlightById(id);
	}
}
