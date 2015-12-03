package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.FlightDao;
import com.happytrip.model.Airline;
import com.happytrip.model.Flight;
import com.happytrip.model.FlightCapacity;
import com.happytrip.model.FlightClass;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;
public class JdbcFlightDao implements FlightDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcFlightDao.class);
	public JdbcFlightDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Flight flight) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs;
		long flightId = 0;
		try {
			con = getMySqlConnection();
			
			String query = "insert into flights(flightname,airlineid)" +
					" values(?,?)";
			String addCapacityQuery = "insert into flightcapacity(flightid, classid, totalseats) values(?,?,?)";
			stmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, flight.getFlightName());
			stmt.setLong(2,flight.getAirline().getAirlineId());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()){
				flightId = rs.getLong(1);
			}
			
			for(FlightCapacity capacity : flight.getCapacity()){
				stmt = con.prepareStatement(addCapacityQuery);
				stmt.setLong(1, flightId);
				stmt.setLong(2, capacity.getFlightClass().getClassId());
				stmt.setLong(3, capacity.getTotalSeats());
				stmt.addBatch();
			}
			
			if(flight.getCapacity().size() > 0){
				stmt.executeBatch();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}

	}

	@Override
	public Flight findByFlightName(String flightName) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			
			String query = "select * from flights where flightname=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, flightName);
			result = stmt.executeQuery();
			result.next();
			Flight flight = new Flight();
			flight.setFlightId(result.getLong(1));
			flight.setFlightName(result.getString(2));
			flight.setAirline(new Airline(result.getLong(1)));
			return flight;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}

	}

	@Override
	public Flight findByFlightId(long flightId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			
			String query = "select * from flights where flightid=?";
			stmt = con.prepareStatement(query);
			stmt.setLong(1, flightId);
			result = stmt.executeQuery();
			result.next();
			Flight flight = new Flight();
			flight.setFlightId(result.getLong(1));
			flight.setFlightName(result.getString(2));
			flight.setAirline(new Airline(result.getLong(1)));
			return flight;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public List<Flight> getAllFlights() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			String query = "select * from flights";
			List<Flight> flights = new ArrayList<Flight>();
			stmt = con.createStatement();
			result = stmt.executeQuery(query);
			while(result.next())
			{
				Flight flight = new Flight();
				flight.setFlightId(result.getLong(1));
				flight.setFlightName(result.getString(2));
				flight.setAirline(new Airline(result.getLong(1)));
				flights.add(flight);
			}
			return flights;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public List<FlightCapacity> getCapacitiesForFlightId(long flightId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			String query = "select * from flightcapacity fc where fc.flightid=?";
			List<FlightCapacity> flightCapacities = new ArrayList<FlightCapacity>();
			stmt = con.prepareStatement(query);
			stmt.setLong(1, flightId);
			result = stmt.executeQuery();
			while(result.next())
			{
				FlightCapacity flightCapacity = new FlightCapacity();
				flightCapacity.setId(result.getLong("id"));
				flightCapacity.setTotalSeats(result.getInt("totalseats"));
				flightCapacity.setFlight(new Flight(result.getLong("flightid")));
				flightCapacity.setFlightClass(new FlightClass(result.getLong("classid")));
				flightCapacities.add(flightCapacity);
			}
			return flightCapacities;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

}
