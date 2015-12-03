package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.LookupDao;
import com.happytrip.model.City;
import com.happytrip.model.FlightClass;
import com.happytrip.model.Role;
import com.happytrip.model.State;
import com.happytrip.model.User;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;
public class JdbcLookupDao implements LookupDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcLookupDao.class);
	public JdbcLookupDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FlightClass findForClassName(String className) {
		String routeQuery = "select * from classes where classtype=?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement(routeQuery);
			stmt.setString(1,className);
			result = stmt.executeQuery();
			if(result.next()){
				FlightClass flightClass = new FlightClass();
				flightClass.setClassId(result.getLong(1));
				flightClass.setClassType(result.getString(2));
				con.close();
				return flightClass;	
			}
			return null;
			
		}catch(Exception e)
		{
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public List<FlightClass> findAllFlightClasses() {
		String routeQuery = "select * from classes";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(routeQuery);
			result = stmt.executeQuery();
			List<FlightClass> flightClasses = new ArrayList<FlightClass>();
			while(result.next())
			{
				FlightClass flightClass = new FlightClass();
				flightClass.setClassId(result.getLong(1));
				flightClass.setClassType(result.getString(2));	
				flightClasses.add(flightClass);
			}
			con.close();
			return flightClasses;
		}catch(Exception e)
		{
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public State findForStateName(String stateName) {
		String routeQuery = "select * from states";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(routeQuery);
			result = stmt.executeQuery();
			if(result.next()){
			State state = new State();
			state.setStateId(result.getLong(1));
			state.setStateName(result.getString(2));
			con.close();
			return state;
			}
			return null;
		}catch(Exception e)
		{
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public Role findForRoleName(String roleName) {
		String routeQuery = "select * from user_roles where authority=?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(routeQuery);
			Role role = new Role();
			stmt.setString(1,roleName);
			result = stmt.executeQuery();
			if(result.next()){
				role.setId(result.getLong(1));
				role.setRole(result.getString(2));
				con.close();
				return role;	
			}
			return null;
			
		}catch(Exception e)
		{
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public City findForCityName(String cityName) {
		String routeQuery = "select * from cities where cityname=?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement(routeQuery);
			stmt.setString(1,cityName);
			result = stmt.executeQuery();
			if(result.next()){
				City city = new City();
				city.setCityId(result.getLong(1));
				city.setCityName(result.getString(2));
				State state = new State();
				state.setStateId(result.getLong(3));
				city.setState(state);
				con.close();
				return city;	
			}
		return null;
			
		}catch(Exception e)
		{
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

}
