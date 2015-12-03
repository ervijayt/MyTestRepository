package com.happytrip.dao.jdbc;

import static com.happytrip.dao.jdbc.ConnectionHelper.cleanup;
import static com.happytrip.dao.jdbc.ConnectionHelper.getMySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.report.ReportDao;
import com.happytrip.model.reports.AirlineReport;
import com.happytrip.model.reports.BookingReport;
import com.happytrip.model.reports.RouteReport;
import com.happytrip.model.reports.ScheduledFlightReport;
import com.happytrip.model.reports.UserReport;

public class JdbcReportDao implements ReportDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcGenericDao.class);
	
	@Override
	public void storeUserReport(UserReport usersReport) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "insert into userreport (email, fullname, gender) VALUES (?, ?, ?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, usersReport.getEmail());
			stmt.setString(2,usersReport.getFullName());
			stmt.setString(3,usersReport.getGender());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}

		
	}

	@Override
	public void storeAirlinesReport(AirlineReport airlineReport) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "insert into airlinereport (airlinecode,airlinelogo,airlinename) VALUES (?, ?, ?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, airlineReport.getAirlineCode());
			stmt.setString(2,airlineReport.getAirlineLogo());
			stmt.setString(3,airlineReport.getAirlineName());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}


		
	}

	@Override
	public void storeRouteReport(RouteReport routeReport) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "insert into routereport (fromcity,tocity) VALUES (?, ?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1,routeReport.getFromCity());
			stmt.setString(2,routeReport.getToCity());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}


		
	}

	@Override
	public void storeScheduledFlighReport(
			ScheduledFlightReport scheduledFlightReport) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "insert into scheduledflightreport (arrivaltime,departuretime,flightnumber) VALUES (?, ?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1,scheduledFlightReport.getArrivalTime());
			stmt.setString(2,scheduledFlightReport.getDepartureTime());
			stmt.setString(3,scheduledFlightReport.getFlightNumber());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}
		
	}

	@Override
	public void storeBookingReport(BookingReport bookingReport) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "insert into bookingreport (costperticket, dateofjourney, numberofseats) VALUES (?, ?,?)";
			stmt = con.prepareStatement(query);
			stmt.setDouble(1,bookingReport.getCostPerTicket());
			stmt.setDate(2,new java.sql.Date(bookingReport.getDateOfJourney().getTime()));
			stmt.setInt(3,bookingReport.getNumberOfSeats());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}


		
	}

	@Override
	public List<UserReport> findAllUserReport() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<UserReport> userReports = new ArrayList<UserReport>();
		try {
			con = getMySqlConnection();
			String query = "select * from userreport";
			stmt = con.prepareStatement(query);
			result = stmt.executeQuery();
			UserReport userReport = null;
			while (result.next())
			{
				userReport = new UserReport();
				userReport.setEmail(result.getString(2));
				userReport.setFullName(result.getString(3));
				userReport.setGender(result.getString(4));
				userReports.add(userReport);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}

		return userReports;
	}

	@Override
	public List<AirlineReport> findAllAirlineReport() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<AirlineReport> airlineReports = new ArrayList<AirlineReport>();
		try {
			con = getMySqlConnection();
			String query = "select * from airlinereport";
			stmt = con.prepareStatement(query);
			result = stmt.executeQuery();
			AirlineReport airlineReport = null;
			while (result.next())
			{
				airlineReport = new AirlineReport();
				airlineReport.setAirlineCode(result.getString(2));
				airlineReport.setAirlineLogo(result.getString(3));
				airlineReport.setAirlineName(result.getString(4));
				airlineReports.add(airlineReport);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}

		return airlineReports;
	}

	@Override
	public List<RouteReport> findAllRouteReport() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<RouteReport> routeReports = new ArrayList<RouteReport>();
		try {
			con = getMySqlConnection();
			String query = "select * from routereport";
			stmt = con.prepareStatement(query);
			result = stmt.executeQuery();
			RouteReport routeReport = null;
			while (result.next())
			{
				routeReport = new RouteReport();
				routeReport.setFromCity(result.getString(2));
				routeReport.setToCity(result.getString(3));
				routeReports.add(routeReport);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}

		return routeReports;
	}

	@Override
	public List<ScheduledFlightReport> findAllScheduledFlightReport() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<ScheduledFlightReport> scheduledFlightReports = new ArrayList<ScheduledFlightReport>();
		try {
			con = getMySqlConnection();
			String query = "select * from scheduledflightreport";
			stmt = con.prepareStatement(query);
			result = stmt.executeQuery();
			ScheduledFlightReport scheduledFlightReport = null;
			while (result.next())
			{
				scheduledFlightReport = new ScheduledFlightReport();
				scheduledFlightReport.setArrivalTime(result.getString(2));
				scheduledFlightReport.setDepartureTime(result.getString(3));
				scheduledFlightReport.setFlightNumber(result.getString(4));
				scheduledFlightReports.add(scheduledFlightReport);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}

		return scheduledFlightReports;
	}

	@Override
	public List<BookingReport> findAllBookingReport() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<BookingReport> bookingReports = new ArrayList<BookingReport>();
		try {
			con = getMySqlConnection();
			String query = "select * from bookingreport";
			stmt = con.prepareStatement(query);
			result = stmt.executeQuery();
			BookingReport bookingReport = null;
			while (result.next())
			{
				bookingReport = new BookingReport();
				bookingReport.setCostPerTicket(result.getFloat(2));
				bookingReport.setDateOfJourney(new Date(result.getDate(3).getTime()));
				bookingReport.setNumberOfSeats(result.getInt(4));
				bookingReports.add(bookingReport);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}

		return bookingReports;
	}

}
