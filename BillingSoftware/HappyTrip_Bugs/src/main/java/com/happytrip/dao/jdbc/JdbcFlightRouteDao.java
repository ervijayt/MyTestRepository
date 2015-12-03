package com.happytrip.dao.jdbc;

import static com.happytrip.dao.jdbc.ConnectionHelper.cleanup;
import static com.happytrip.dao.jdbc.ConnectionHelper.getMySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.FlightRouteDao;
import com.happytrip.model.Airline;
import com.happytrip.model.City;
import com.happytrip.model.Flight;
import com.happytrip.model.FlightClass;
import com.happytrip.model.FlightRouteCost;
import com.happytrip.model.Route;
import com.happytrip.model.ScheduledFlight;
import com.happytrip.model.SeatAvailability;
public class JdbcFlightRouteDao implements FlightRouteDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcFlightRouteDao.class);
	public JdbcFlightRouteDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<City> findAllFromCities(String fromCity) {
		System.out.println("FromCity"+fromCity);
		String searchQuery = "select c.cityid,c.cityname from cities c,routes r where r.fromcityid = c.cityid and c.cityname like ?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement(searchQuery);
			stmt.setString(1,fromCity+"%");
			rs = stmt.executeQuery();
			List<City> cities = new ArrayList<City>();
			while(rs.next())
			{
				City city = new City();
				city.setCityId(rs.getLong(1));
				city.setCityName(rs.getString(2));
				cities.add(city);
			}
			System.out.println("Cities"+cities);
			return cities;
		}catch(Exception e)
		{
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, rs);
		}
	}

	@Override
	public List<City> findAllToCity(String toCity) {
		System.out.println(toCity);
		String searchQuery = "select c.cityid,c.cityname from cities c,routes r where r.tocityid = c.cityid and c.cityname like ?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement(searchQuery);
			stmt.setString(1,toCity+"%");
			rs = stmt.executeQuery();
			List<City> cities = new ArrayList<City>();
			while(rs.next())
			{
				City city = new City();
				city.setCityId(rs.getLong(1));
				city.setCityName(rs.getString(2));
				cities.add(city);
			}
			return cities;
		}catch(Exception e)
		{
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, rs);
		}
	}

	public String getSearchQuery()
	{
		return "select " +
				"scheduledf0_.flightrouteid," +
				"flightrout3_.flightroutecostid," +
				"flight4_.flightid," +
				"airline5_.airlineid," +
				"scheduledf0_1_.arrivaltime,"+
				"scheduledf0_1_.departuretime,"+
				"scheduledf0_1_.distanceinkms,"+
				"scheduledf0_1_.durationinmins,"+
				"scheduledf0_1_.flightnumber,"+
				"scheduledf0_1_.routeid,"+
				"scheduledf0_.scheduledflightdate,"+
				"route1_.tocityid,"+
				"route1_.fromcityid,"+
				"flightrout3_.costperticket,"+
				"flightrout3_.classid,"+
				"flightrout3_.flightrouteid,"+
				"flight4_.flightname,"+
				"airline5_.airlinecode,"+
				"airline5_.airlinelogo,"+
				"airline5_.airlinename "+
				"from "+
				"scheduledflight scheduledf0_ "+
				"inner join "+
				"flightroutes scheduledf0_1_ "+ 
				"on scheduledf0_.flightrouteid=scheduledf0_1_.flightrouteid "+ 
				"inner join "+
				"routes route1_ "+ 
				"on scheduledf0_1_.routeid=route1_.routeid "+ 
				"inner join "+
				"seatavailability availabili2_ "+ 
				"on scheduledf0_.flightrouteid=availabili2_.scheduledFlightid "+
				"inner join "+
				"flightroutecosts flightrout3_ "+ 
				"on scheduledf0_.flightrouteid=flightrout3_.flightrouteid "+ 
				"inner join "+
				"flights flight4_ "+ 
				"on scheduledf0_1_.flightid=flight4_.flightid "+ 
				"inner join "+
				"airlines airline5_ "+ 
				"on flight4_.airlineid=airline5_.airlineid cross "+ 
				"join "+
				"cities fromcity cross "+ 
				"join "+
				"cities tocity cross "+ 
				"join "+
				"classes flightclas8_ cross "+ 
				"join "+
				"classes flightclas9_ "+ 
				"where "+
				"route1_.fromcityid=fromcity.cityid "+ 
				"and route1_.tocityid=tocity.cityid "+ 
				"and availabili2_.classid=flightclas8_.classid "+ 
				"and flightrout3_.classid=flightclas9_.classid "+
				"and ("+
				"fromcity.cityname like ?"+
				") "+ 
				"and ("+
				"tocity.cityname like ?"+
				") "+
				"and scheduledf0_.scheduledflightdate=? "+
				"and ("+
				"flightclas8_.classtype like ?"+
				") "+ 
				"and availabili2_.availableseats>? "+ 
				"and ("+
				"flightclas9_.classtype like ?)";
	}

	public String getSearchQueryType2()
	{
		return "select " +
				"scheduledf0_.flightrouteid," +
				"flightrout3_.flightroutecostid," +
				"flight4_.flightid," +
				"airline5_.airlineid," +
				"scheduledf0_1_.arrivaltime,"+
				"scheduledf0_1_.departuretime,"+
				"scheduledf0_1_.distanceinkms,"+
				"scheduledf0_1_.durationinmins,"+
				"scheduledf0_1_.flightnumber,"+
				"scheduledf0_1_.routeid,"+
				"scheduledf0_.scheduledflightdate,"+
				"route1_.tocityid,"+
				"route1_.fromcityid,"+
				"flightrout3_.costperticket,"+
				"flightrout3_.classid,"+
				"flightrout3_.flightrouteid,"+
				"flight4_.flightname,"+
				"airline5_.airlinecode,"+
				"airline5_.airlinelogo,"+
				"airline5_.airlinename "+
				"from "+
				"scheduledflight scheduledf0_ "+
				"inner join "+
				"flightroutes scheduledf0_1_ "+ 
				"on scheduledf0_.flightrouteid=scheduledf0_1_.flightrouteid "+ 
				"inner join "+
				"routes route1_ "+ 
				"on scheduledf0_1_.routeid=route1_.routeid "+ 
				"inner join "+
				"seatavailability availabili2_ "+ 
				"on scheduledf0_.flightrouteid=availabili2_.scheduledFlightid "+
				"inner join "+
				"flightroutecosts flightrout3_ "+ 
				"on scheduledf0_.flightrouteid=flightrout3_.flightrouteid "+ 
				"inner join "+
				"flights flight4_ "+ 
				"on scheduledf0_1_.flightid=flight4_.flightid "+ 
				"inner join "+
				"airlines airline5_ "+ 
				"on flight4_.airlineid=airline5_.airlineid cross "+ 
				"join "+
				"cities fromcity cross "+ 
				"join "+
				"cities tocity cross "+ 
				"join "+
				"classes flightclas8_ cross "+ 
				"join "+
				"classes flightclas9_ "+ 
				"where "+
				"route1_.fromcityid=fromcity.cityid "+ 
				"and route1_.tocityid=tocity.cityid "+ 
				"and availabili2_.classid=flightclas8_.classid "+ 
				"and flightrout3_.classid=flightclas9_.classid "+
				"and scheduledf0_.FlightRouteId=? "; 
	}


	public String getSearchQueryType1()
	{
		return "select " +
				"scheduledf0_.flightrouteid," +
				"flightrout3_.flightroutecostid," +
				"flight4_.flightid," +
				"airline5_.airlineid," +
				"scheduledf0_1_.arrivaltime,"+
				"scheduledf0_1_.departuretime,"+
				"scheduledf0_1_.distanceinkms,"+
				"scheduledf0_1_.durationinmins,"+
				"scheduledf0_1_.flightnumber,"+
				"scheduledf0_1_.routeid,"+
				"scheduledf0_.scheduledflightdate,"+
				"route1_.tocityid,"+
				"route1_.fromcityid,"+
				"flightrout3_.costperticket,"+
				"flightrout3_.classid,"+
				"flightrout3_.flightrouteid,"+
				"flight4_.flightname,"+
				"airline5_.airlinecode,"+
				"airline5_.airlinelogo,"+
				"airline5_.airlinename "+
				"from "+
				"scheduledflight scheduledf0_ "+
				"inner join "+
				"flightroutes scheduledf0_1_ "+ 
				"on scheduledf0_.flightrouteid=scheduledf0_1_.flightrouteid "+ 
				"inner join "+
				"routes route1_ "+ 
				"on scheduledf0_1_.routeid=route1_.routeid "+ 
				"inner join "+
				"seatavailability availabili2_ "+ 
				"on scheduledf0_.flightrouteid=availabili2_.scheduledFlightid "+
				"inner join "+
				"flightroutecosts flightrout3_ "+ 
				"on scheduledf0_.flightrouteid=flightrout3_.flightrouteid "+ 
				"inner join "+
				"flights flight4_ "+ 
				"on scheduledf0_1_.flightid=flight4_.flightid "+ 
				"inner join "+
				"airlines airline5_ "+ 
				"on flight4_.airlineid=airline5_.airlineid cross "+ 
				"join "+
				"cities fromcity cross "+ 
				"join "+
				"cities tocity cross "+ 
				"join "+
				"classes flightclas8_ cross "+ 
				"join "+
				"classes flightclas9_ "+ 
				"where "+
				"route1_.fromcityid=fromcity.cityid "+ 
				"and route1_.tocityid=tocity.cityid "+ 
				"and availabili2_.classid=flightclas8_.classid "+ 
				"and flightrout3_.classid=flightclas9_.classid and"+
				"("+
				"flightclas8_.classtype like ?"+
				") "+ 
				"and scheduledf0_.FlightRouteId=? "+ 
				"and ("+
				"flightclas9_.classtype like ?)";
	}
	@Override
	public List<ScheduledFlight> searchFlights(String fromCity, String toCity,
			Date dateOfTravel, FlightClass classOfFlight, int paxNo) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<ScheduledFlight> scheduledFlights = new ArrayList<ScheduledFlight>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			con = getMySqlConnection();
			String searchQuery = getSearchQuery();
			System.out.println(searchQuery);
			stmt = con.prepareStatement(searchQuery);
			stmt.setString(1,fromCity);
			stmt.setString(2,toCity);
			stmt.setString(3,sf.format(dateOfTravel));
			stmt.setString(4,classOfFlight.getClassType());
			stmt.setInt(5,paxNo);
			stmt.setString(6,classOfFlight.getClassType());
			result = stmt.executeQuery();
			ScheduledFlight scheduledFlight = null;
			while(result.next())
			{
				scheduledFlight = new ScheduledFlight();
				scheduledFlight.setFlightRouteId(result.getInt(1));
				FlightRouteCost cost = new FlightRouteCost();
				scheduledFlight.addFlightRouteCost(cost);
				Flight flight = new Flight();
				Airline airline = new Airline();
				Route route = new Route();
				flight.setAirline(airline);
				scheduledFlight.setFlight(flight);
				scheduledFlight.setFlight(flight);
				cost.setFlightRouteCostId(result.getInt(2));
				flight.setFlightId(result.getInt(3));
				airline.setAirlineId(result.getLong(4));
				scheduledFlight.setArrivalTime(result.getString(5));
				scheduledFlight.setDepartureTime(result.getString(6));
				scheduledFlight.setDistanceInKms(result.getInt(7));
				scheduledFlight.setDurationInMins(result.getInt(8));
				scheduledFlight.setFlightNumber(result.getString(9));
				route.setRouteId(result.getLong(10));
				scheduledFlight.setRoute(route);
				scheduledFlight.setScheduledFlightDate(result.getDate(11));
				route.setToCity(new City(result.getLong(12)));
				route.setFromCity(new City(result.getLong(13)));
				cost.setCostPerTicket(result.getFloat(14));
				cost.setFlightClass(new FlightClass(result.getInt(15)));
				scheduledFlight.setFlightRouteId(result.getInt(16));
				flight.setFlightName(result.getString(17));
				airline.setAirlineCode(result.getString(18));
				airline.setAirlineLogo(result.getString(19));
				airline.setAirlineName(result.getString(20));
				scheduledFlights.add(scheduledFlight);
			}
			con.close();
			System.out.println("Scheduled Flights"+scheduledFlight);
			if(paxNo>2){
				for(int i=0 ; i<=scheduledFlights.size();i++){
					LOGGER.debug(scheduledFlights.get(i).getArrivalTime());
				}
			}
			return scheduledFlights;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

	@Override
	public void saveScheduledFlight(ScheduledFlight scheduledFlight) {
		String flightRouteQuery = "insert into flightroutes(arrivaltime, departuretime, distanceinkms, durationinmins, flightnumber,flightid, routeid) values(?, ?, ?, ?, ?, ?, ?)";
		String scheduleFlightQuery = "insert into scheduledflight(scheduledflightdate, flightrouteid)values(?, ?)";
		String flightCostQuery = "insert into flightroutecosts(costperticket, classid, flightrouteid) values(?, ?, ?)";
		String seatAvailabilityQuery = "insert into seatavailability(scheduledflightid, classid, availableseats)values(?, ?, ?)";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {


			con = getMySqlConnection();
			stmt = con.prepareStatement(flightRouteQuery,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,scheduledFlight.getArrivalTime());
			stmt.setString(2,scheduledFlight.getDepartureTime());
			stmt.setInt(3,scheduledFlight.getDistanceInKms());
			stmt.setInt(4,scheduledFlight.getDurationInMins());
			stmt.setString(5,scheduledFlight.getFlightNumber());
			stmt.setLong(6,scheduledFlight.getFlight().getFlightId());
			stmt.setLong(7,scheduledFlight.getRoute().getRouteId());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if(rs.next()){
				scheduledFlight.setFlightRouteId(rs.getLong(1));
			}

			stmt = con.prepareStatement(scheduleFlightQuery,Statement.RETURN_GENERATED_KEYS);
			stmt.setDate(1,new java.sql.Date(scheduledFlight.getScheduledFlightDate().getTime()));
			stmt.setLong(2,scheduledFlight.getFlightRouteId());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			int scheduleFlightId = (int)scheduledFlight.getFlightRouteId();
			while(rs.next())
			{
				scheduleFlightId = rs.getInt(1);
			}


			stmt = con.prepareStatement(flightCostQuery);
			for(FlightRouteCost c : scheduledFlight.getFlightRouteCosts())
			{
				stmt.setFloat(1,c.getCostPerTicket());
				stmt.setLong(2,c.getFlightClass().getClassId());
				stmt.setLong(3, scheduledFlight.getFlightRouteId());
				stmt.addBatch();
			}
			stmt.executeBatch();

			stmt = con.prepareStatement(seatAvailabilityQuery);
			for(SeatAvailability c : scheduledFlight.getAvailability())
			{
				stmt.setInt(1, scheduleFlightId);
				stmt.setLong(2,c.getFlightClass().getClassId());
				stmt.setInt(3,c.getAvailableSeats());
				stmt.addBatch();
			}
			stmt.executeBatch();
			con.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, rs);
		}
	}

	@Override
	public ScheduledFlight findDetailsById(long id, String classOfFlight) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			String searchQuery = getSearchQueryType1();
			stmt = con.prepareStatement(searchQuery);
			System.out.println("Mysadasdas!!!!!!!!!"+searchQuery);
			stmt.setString(1,classOfFlight);
			stmt.setLong(2,id);
			stmt.setString(3,classOfFlight);
			
			result = stmt.executeQuery();
			ScheduledFlight scheduledFlight = null;
			
			result.next();
			
			scheduledFlight = new ScheduledFlight();
			scheduledFlight.setFlightRouteId(result.getInt(1));
			FlightRouteCost cost = new FlightRouteCost();
			scheduledFlight.addFlightRouteCost(cost);
			Flight flight = new Flight();
			Airline airline = new Airline();
			Route route = new Route();
			flight.setAirline(airline);
			scheduledFlight.setFlight(flight);
			scheduledFlight.setFlight(flight);
			cost.setFlightRouteCostId(result.getInt(2));
			flight.setFlightId(result.getInt(3));
			airline.setAirlineId(result.getLong(4));
			scheduledFlight.setArrivalTime(result.getString(5));
			scheduledFlight.setDepartureTime(result.getString(6));
			scheduledFlight.setDistanceInKms(result.getInt(7));
			scheduledFlight.setDurationInMins(result.getInt(8));
			scheduledFlight.setFlightNumber(result.getString(9));
			route.setRouteId(result.getLong(10));
			scheduledFlight.setRoute(route);
			scheduledFlight.setScheduledFlightDate(new java.sql.Date(result.getDate(11).getTime()));
			route.setToCity(new City(result.getLong(12)));
			route.setFromCity(new City(result.getLong(13)));
			cost.setCostPerTicket(result.getFloat(14));
			cost.setFlightClass(new FlightClass(result.getInt(15)));
			scheduledFlight.setFlightRouteId(result.getInt(16));
			flight.setFlightName(result.getString(17));
			airline.setAirlineCode(result.getString(18));
			airline.setAirlineLogo(result.getString(19));
			airline.setAirlineName(result.getString(20));
			con.close();
			return scheduledFlight;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);

		}
	}

	@Override
	public ScheduledFlight findScheduledFlightById(long id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			String searchQuery = getSearchQueryType2();
			stmt = con.prepareStatement(searchQuery);
			stmt.setLong(1,id);
			result = stmt.executeQuery();
			ScheduledFlight scheduledFlight = null;
			result.next();
			scheduledFlight = new ScheduledFlight();
			scheduledFlight.setFlightRouteId(result.getInt(1));
			FlightRouteCost cost = new FlightRouteCost();
			scheduledFlight.addFlightRouteCost(cost);
			Flight flight = new Flight();
			Airline airline = new Airline();
			Route route = new Route();
			flight.setAirline(airline);
			scheduledFlight.setFlight(flight);
			scheduledFlight.setFlight(flight);
			cost.setFlightRouteCostId(result.getInt(2));
			flight.setFlightId(result.getInt(3));
			airline.setAirlineId(result.getLong(4));
			scheduledFlight.setArrivalTime(result.getString(5));
			scheduledFlight.setDepartureTime(result.getString(6));
			scheduledFlight.setDistanceInKms(result.getInt(7));
			scheduledFlight.setDurationInMins(result.getInt(8));
			scheduledFlight.setFlightNumber(result.getString(9));
			route.setRouteId(result.getLong(10));
			scheduledFlight.setRoute(route);
			scheduledFlight.setScheduledFlightDate(new java.sql.Date(result.getDate(11).getTime()));
			route.setToCity(new City(result.getLong(12)));
			route.setFromCity(new City(result.getLong(13)));
			cost.setCostPerTicket(result.getFloat(14));
			cost.setFlightClass(new FlightClass(result.getInt(15)));
			scheduledFlight.setFlightRouteId(result.getInt(16));
			flight.setFlightName(result.getString(17));
			airline.setAirlineCode(result.getString(18));
			airline.setAirlineLogo(result.getString(19));
			airline.setAirlineName(result.getString(20));
			con.close();
			return scheduledFlight;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);

		}
	}

}
