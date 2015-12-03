package com.happytrip.services.impl;

import java.util.List;
import com.happytrip.dao.BookingDao;
import com.happytrip.dao.jdbc.JdbcBookingDao;
import com.happytrip.model.FlightBooking;
import com.happytrip.services.BookingService;
import com.happytrip.util.BeanFactory;

public class BookingServiceImpl implements BookingService {
	
	private BookingDao bookingDao;
	
	public BookingServiceImpl() {
		bookingDao = new JdbcBookingDao();
	}



	@Override
	public List<FlightBooking> findAllFlightBookingsForUser(String userName) {
		return bookingDao.getAllFlightBookingsForUser(userName);
	}

}
