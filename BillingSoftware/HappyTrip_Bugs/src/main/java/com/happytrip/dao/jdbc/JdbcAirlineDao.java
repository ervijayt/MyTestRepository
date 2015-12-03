package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.AirlineDao;
import com.happytrip.model.Airline;
import com.mysql.jdbc.Statement;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;
public class JdbcAirlineDao implements AirlineDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcAirlineDao.class);

	
	@Override
	public void save(Airline airline) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			
			String query = "insert into airlines(airlinecode,airlinename)" +
					" values(?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, airline.getAirlineCode());
			stmt.setString(2, airline.getAirlineName());
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
	public Airline findByAirlineCode(String code) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			String query = "select * from airlines where airlinecode like ?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, code);
			result = stmt.executeQuery();
			Airline airline = null;
			List<Airline>airlines = new ArrayList<Airline>();
			if(result.next()){
				airline = new Airline();
				airline.setAirlineCode(result.getString("airlinecode"));
				airline.setAirlineId(result.getInt("airlineid"));
				airline.setAirlineName(result.getString("airlinename"));
				airline.setAirlineLogo(result.getString("airlinelogo"));
				airlines.add(airline);
			}
			con.close();
			if(airlines.size() > 0){
				if(code.length()>10){
					for(int i=0;i<=airlines.size();i++){
						LOGGER.debug(airlines.get(i).getAirlineCode());
					}
				}
				return airlines.get(0);
			}
			return null;
		}
		catch(Exception e){
			LOGGER.debug("Error in JdbcBugsDao");
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public List<Airline> findAll() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from airlines");
			List<Airline> airlines = new ArrayList<Airline>();
			Airline airline;
			while(result.next()){
				airline = new Airline();
				airline.setAirlineCode(result.getString("airlinecode"));
				airline.setAirlineId(result.getInt("airlineid"));
				airline.setAirlineName(result.getString("airlinename"));
				airline.setAirlineLogo(result.getString("airlinelogo"));
				airlines.add(airline);
			}
			con.close();
			return airlines;
		}
		catch(Exception e){
			LOGGER.debug("Error in JdbcBugsDao");
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

}
