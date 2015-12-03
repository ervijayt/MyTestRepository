package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.BookingDao;
import com.happytrip.model.Airline;
import com.happytrip.model.FlightBooking;
import com.mysql.jdbc.Statement;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;

public class JdbcBookingDao implements BookingDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcBookingDao.class);

	public JdbcBookingDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<FlightBooking> getAllFlightBookingsForUser(String userName) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from flightbookings");
			List<FlightBooking> flightBookings = new ArrayList<FlightBooking>();
			FlightBooking flightBooking;
			while(result.next()){
				flightBooking = new FlightBooking();
			}
			con.close();
			return flightBookings;
		}
		catch(Exception e){
			LOGGER.debug("Error in JdbcBugsDao");
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

}
