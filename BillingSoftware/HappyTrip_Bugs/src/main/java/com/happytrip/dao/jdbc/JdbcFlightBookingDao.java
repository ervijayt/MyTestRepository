package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.FlightBookingDao;
import com.happytrip.model.FlightBooking;
import com.happytrip.model.Passenger;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;
public class JdbcFlightBookingDao implements FlightBookingDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcFlightBookingDao.class);
	public JdbcFlightBookingDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(FlightBooking booking) {
		String queryString1 = "INSERT INTO `happytrip`.`flightbookings` (`costperticket`, `dateofjourney`, `noofseats`, `classid`, `flightrouteid`) VALUES (?,?,?,?,?)";
		String queryString2 = "INSERT INTO `happytrip`.`passengers` (`dateofbirth`, `gender`, `name`, `title`, `bookingid`) VALUES (?,?, ?,?,?)";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement(queryString1,Statement.RETURN_GENERATED_KEYS);
			stmt.setFloat(1,booking.getCostPerTicket());
			stmt.setString(2,booking.getDateOfJourney().toLocaleString());
			stmt.setInt(3,booking.getNoOfSeats());
			stmt.setLong(4,booking.getFlightClass().getClassId());
			stmt.setLong(5,booking.getFlightRoute().getFlightRouteId());
			stmt.executeUpdate();
			ResultSet keySet = stmt.getGeneratedKeys();
			keySet.next();
			int bookingId = keySet.getInt(1);
			stmt = con.prepareStatement(queryString2);
			for(Passenger pass : booking.getPassengers())
			{
				stmt.setString(1,pass.getDateOfBirth().toLocaleString());
				stmt.setString(2,pass.getGender());
				stmt.setString(3,pass.getName());
				stmt.setString(4,pass.getTitle());
				stmt.setLong(5,bookingId);
				stmt.addBatch();
			}
			stmt.executeBatch();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}

	}

}
