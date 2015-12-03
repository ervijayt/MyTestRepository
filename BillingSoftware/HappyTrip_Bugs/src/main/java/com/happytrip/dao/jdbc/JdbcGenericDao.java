package com.happytrip.dao.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.GenericDao;
import com.happytrip.model.Airline;
import com.happytrip.model.City;
import com.happytrip.model.FlightClass;
import com.happytrip.model.Route;
import com.mysql.jdbc.Statement;
import static com.happytrip.dao.jdbc.ConnectionHelper.*;

public class JdbcGenericDao implements GenericDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcGenericDao.class);

	@Override
	public List<City> getAllCities() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from cities");
			List<City> cities = new ArrayList<City>();
			City city;
			while(result.next()){
				city = new City();
				city.setCityId(result.getInt("cityid"));
				city.setCityName("");
				cities.add(city);
			}
			con.close();
			return cities;
		}
		catch(Exception e){
			LOGGER.debug("Error in JdbcBugsDao.getAllCities");
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
		
	}

	@Override
	public List<Airline> getAllAirlines() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from airlines");
			List<Airline> airlines = new ArrayList<Airline>();
			Airline airline;
			while(result.next()){
				airline = new Airline();
				airline.setAirlineId(result.getInt("airlineid"));
				airlines.add(airline);
			}
			con.close();
			return airlines;
		}
		catch(Exception e){
			LOGGER.debug("Error in JdbcBugsDao");
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con,stmt,result);
		}
	}

	@Override
	public List<FlightClass> findAllFlightClasses() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from classes");
			List<FlightClass> classes = new ArrayList<FlightClass>();
			FlightClass flightClass;
			while(result.next()){
				flightClass = new FlightClass();
				flightClass.setClassId(result.getInt("classid"));
				flightClass.setClassType("");
			}
			con.close();
			return classes;
		}
		catch(Exception e){
			LOGGER.debug("Error in JdbcBugsDao");
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public void saveAirline(Airline airline) {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate("insert into airlines(airlinecode,airlinename) values('"+airline.getAirlineCode()+"','"+airline.getAirlineName()+"')");
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Error in JdbcBugsDao");
			throw new RuntimeException(e.getMessage());
		} finally{
			cleanup(con, stmt, null);
		}
		

	}

	@Override
	public void saveRoute(Route route) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate("insert into routes(fromcityid,tocityid) values('"+route.getFromCity().getCityId()+"','"+route.getToCity().getCityId()+"')");
			con.close();
		}
		catch(Exception e){
			LOGGER.debug("Error in JdbcBugsDao");
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}
	}
}
