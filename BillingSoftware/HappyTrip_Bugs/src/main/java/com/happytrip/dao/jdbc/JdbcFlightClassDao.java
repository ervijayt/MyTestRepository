package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.FlightClassDao;
import com.happytrip.model.FlightClass;
import com.happytrip.model.Passenger;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;
public class JdbcFlightClassDao implements FlightClassDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcFlightClassDao.class);
	public JdbcFlightClassDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(FlightClass flightClass) {
		Connection con = null;
		PreparedStatement stmt = null;
		String query = "INSERT INTO classes (classtype) VALUES (?)";
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1,flightClass.getClassType());
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}


	}

}
