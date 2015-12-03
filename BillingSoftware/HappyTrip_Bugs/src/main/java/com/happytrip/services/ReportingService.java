package com.happytrip.services;

import java.util.List;

import com.happytrip.model.reports.AirlineReport;
import com.happytrip.model.reports.BookingReport;
import com.happytrip.model.reports.RouteReport;
import com.happytrip.model.reports.ScheduledFlightReport;
import com.happytrip.model.reports.UserReport;

public interface ReportingService {

	void storeUserReport(UserReport usersReport);
	void storeAirlinesReport(AirlineReport airlineReport);
	void storeRouteReport(RouteReport routeReport);
	void storeScheduledFlighReport(ScheduledFlightReport scheduledFlightReport);
	void storeBookingReport(BookingReport bookingReport);
	
	List<UserReport> findAllUserReport();
	List<AirlineReport> findAllAirlineReport();
	List<RouteReport> findAllRouteReport();
	List<ScheduledFlightReport> findAllScheduledFlightReport();
	List<BookingReport> findAllBookingReport();
}
