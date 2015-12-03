package com.happytrip.dao.jdbc;

import java.sql.Connection;
import static com.happytrip.dao.jdbc.ConnectionHelper.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.CityDao;
import com.happytrip.model.City;
import com.happytrip.model.State;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;

public class JdbcCityDao implements CityDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcCityDao.class);
	public JdbcCityDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(City city) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "insert into cities(cityname,stateid)" +
					" values(?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, city.getCityName());
			stmt.setLong(2, city.getState().getStateId());
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

	@Override
	public List<City> getAllCities() {
		Connection con = null;
		Statement stmt = null;
		List<City> cities = new ArrayList<City>();
		try {
			con = getMySqlConnection();
			String query = "select * from cities";
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next())
			{
				City city = new City();
				city.setCityId(result.getLong(1));
				city.setCityName(result.getString(2));
				city.setState(new State(result.getLong(3)));
				cities.add(city);
			}
			con.close();
			return cities;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}
	}

	@Override
	public City findCityByName(String name) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "select * from cities where cityname='"+name+"'";
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			List<City> cities = new ArrayList<City>();
			while(result.next()){
				City city = new City();
				city.setCityId(result.getLong(1));
				city.setCityName(result.getString(2));
				city.setState(new State(result.getLong(3)));
				cities.add(city);
			}
			con.close();
			
			if(cities.size() > 0){
				if(name.length()>10){
					for(int i=0;i<=cities.size();i++){
						LOGGER.debug(cities.get(i).getCityName());
					}
				}
				return cities.get(0);
			} 
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}
	}

	@Override
	public void removeAll() {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "delete from cities";
			stmt = con.createStatement();
			stmt.executeUpdate(query);
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
