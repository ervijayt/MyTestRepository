package com.happytrip.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.happytrip.dao.BackupDao;
import com.happytrip.dao.CityDao;
import com.happytrip.model.reports.AirlineReport;
import com.happytrip.model.reports.BookingReport;
import com.happytrip.model.reports.RouteReport;
import com.happytrip.model.reports.ScheduledFlightReport;
import com.happytrip.model.reports.UserReport;
import com.happytrip.services.BookingService;
import com.happytrip.services.ReportingService;
import com.happytrip.services.impl.ReportingServiceImpl;
import com.happytrip.util.BeanFactory;

@Controller
public class ReportController {

	
	private ReportingService reportingService;
	
	public ReportController() {
		reportingService = new ReportingServiceImpl();
	}
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	@RequestMapping(value = "/admin/report/listAllUsers.html", method = RequestMethod.GET)
	public String listAllUsers(Model model) {
		List<UserReport> reports = reportingService.findAllUserReport();
		model.addAttribute("report", reports);
		return "/admin/report/users";
	}
	
	@RequestMapping(value = "/admin/report/listAllBookings.html", method = RequestMethod.GET)
	public ModelAndView listAllBookings(){
		List<BookingReport> reports = reportingService.findAllBookingReport();
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("report", reports);
		return new ModelAndView("/admin/report/bookings",models);
	}
	
	@RequestMapping(value = "/admin/report/listAllAirline.html", method = RequestMethod.GET)
	public ModelAndView listAllAirlines(){
		List<AirlineReport> reports = reportingService.findAllAirlineReport();
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("report", reports);
		return new ModelAndView("/admin/report/airlines",models);
	}
	
	@RequestMapping(value = "/admin/report/listAllScheduledFlight.html", method = RequestMethod.GET)
	public ModelAndView listAllScheduledFlight(){
		List<ScheduledFlightReport> reports = reportingService.findAllScheduledFlightReport();
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("report", reports);
		return new ModelAndView("/admin/report/scheduledFlights",models);
	}
	
	@RequestMapping(value = "/admin/report/listAllRoutes.html", method = RequestMethod.GET)
	public ModelAndView listAllRoutes(){
		List<RouteReport> reports = reportingService.findAllRouteReport();
			Map<String, Object> models = new HashMap<String, Object>();
		models.put("report", reports);
		return new ModelAndView("/admin/report/routes",models);
	}
}
