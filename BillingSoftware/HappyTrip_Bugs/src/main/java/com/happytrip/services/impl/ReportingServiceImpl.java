package com.happytrip.services.impl;

import java.util.List;

import com.happytrip.dao.jdbc.JdbcReportDao;
import com.happytrip.dao.report.ReportDao;
import com.happytrip.model.reports.AirlineReport;
import com.happytrip.model.reports.BookingReport;
import com.happytrip.model.reports.RouteReport;
import com.happytrip.model.reports.ScheduledFlightReport;
import com.happytrip.model.reports.UserReport;
import com.happytrip.services.ReportingService;
import com.happytrip.util.BeanFactory;

public class ReportingServiceImpl implements ReportingService {

	private ReportDao reportDao;

	public ReportingServiceImpl() {
		reportDao = new JdbcReportDao();
	}

	@Override
	public void storeUserReport(UserReport usersReport) {
		// TODO Auto-generated method stub
		reportDao.storeUserReport(usersReport);
	}

	@Override
	public void storeAirlinesReport(AirlineReport airlineReport) {
		// TODO Auto-generated method stub
		reportDao.storeAirlinesReport(airlineReport);
	}

	@Override
	public void storeRouteReport(RouteReport routeReport) {
		// TODO Auto-generated method stub
		reportDao.storeRouteReport(routeReport);
	}

	@Override
	public void storeScheduledFlighReport(
			ScheduledFlightReport scheduledFlightReport) {
		// TODO Auto-generated method stub
		reportDao.storeScheduledFlighReport(scheduledFlightReport);
	}

	@Override
	public void storeBookingReport(BookingReport bookingReport) {
		// TODO Auto-generated method stub
		reportDao.storeBookingReport(bookingReport);
	}

	@Override
	public List<UserReport> findAllUserReport() {
		// TODO Auto-generated method stub
		return reportDao.findAllUserReport();
	}

	@Override
	public List<AirlineReport> findAllAirlineReport() {
		// TODO Auto-generated method stub

		return reportDao.findAllAirlineReport();
	}

	@Override
	public List<RouteReport> findAllRouteReport() {
		// TODO Auto-generated method stub
		return reportDao.findAllRouteReport();
	}

	@Override
	public List<ScheduledFlightReport> findAllScheduledFlightReport() {
		// TODO Auto-generated method stub

		return reportDao.findAllScheduledFlightReport();
	}

	@Override
	public List<BookingReport> findAllBookingReport() {
		// TODO Auto-generated method stub

		return reportDao.findAllBookingReport();
	}

}
