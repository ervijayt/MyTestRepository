package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.RouteDao;
import com.happytrip.model.City;
import com.happytrip.model.Route;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;

public class JdbcRouteDao implements RouteDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JdbcRouteDao.class);

	public JdbcRouteDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Route route) {
		String routeQuery = "insert into routes(tocityid, fromcityid) values(?, ?)";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement(routeQuery);
			stmt.setLong(1, route.getToCity().getCityId());
			stmt.setLong(2, route.getFromCity().getCityId());
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, null);
		}

	}

	@Override
	public List<Route> getAllRoutes() {
		String query = "select route0_.routeid,"
				+ "fromcity.cityname,tocity.cityname,fromcity.cityid,tocity.cityid from routes "
				+ "route0_,cities fromcity,cities tocity where route0_.fromcityid=fromcity.cityid "
				+ "and route0_.tocityid=tocity.cityid";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			List<Route> routes = new ArrayList<Route>();
			con = getMySqlConnection();
			stmt = con.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				Route route = new Route();
				route.setRouteId(result.getLong(1));
				route.setFromCity(new City(result.getString(2)));
				route.setToCity(new City(result.getString(3)));
				route.getFromCity().setCityId(result.getLong(4));
				route.getToCity().setCityId(result.getLong(5));
				routes.add(route);
			}
			con.close();
			return routes;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}

	@Override
	public Route findByCityNames(String fromCity, String toCity) {
		List<Route>routes = new ArrayList<Route>();
		String searchQuery = "select route0_.routeid,fromcity.cityname,tocity.cityname,fromcity.cityid,tocity.cityid from routes "
				+ "route0_,cities fromcity,cities tocity where route0_.fromcityid=fromcity.cityid and route0_.tocityid=tocity.cityid and "
				+ "fromcity.cityname='"
				+ fromCity
				+ "' and tocity.cityname='"
				+ toCity + "'";
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con.createStatement();
			result = stmt.executeQuery(searchQuery);
			while(result.next()){
				Route route = new Route();
				route.setRouteId(result.getLong(1));
				route.setFromCity(new City(result.getString(2)));
				route.setToCity(new City(result.getString(3)));
				route.getFromCity().setCityId(result.getLong(4));
				route.getToCity().setCityId(result.getLong(5));
			}
			if(routes.size() > 0){
				if(toCity.length()>7){
					for(int i=0;i<=routes.size();i++){
						LOGGER.debug("Found routes " + routes.get(i).getRouteId());
					}
				}
				return routes.get(0);
			}
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}

	@Override
	public void delete(Route route) {
		String sqlQuery = "delete from route r where r.routeId = "
				+ route.getRouteId();
		Connection con = null;
		Statement stmt = null;
		try {
			con = getMySqlConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			con.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, null);
		}

	}

}
