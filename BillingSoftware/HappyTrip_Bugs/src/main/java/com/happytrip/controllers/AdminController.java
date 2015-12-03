package com.happytrip.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.happytrip.controllers.dto.flight.AirlineDto;
import com.happytrip.controllers.dto.flight.FlightScheduleDto;
import com.happytrip.controllers.dto.flight.RouteDto;
import com.happytrip.dao.BackupDao;
import com.happytrip.dao.UserDao;
import com.happytrip.dao.jdbc.JdbcBackupDao;
import com.happytrip.model.*;
import com.happytrip.services.AdminService;
import com.happytrip.services.UserProfileService;
import com.happytrip.services.impl.AdminServiceImpl;
import com.happytrip.services.impl.UserProfileServiceImpl;
import com.happytrip.util.AuthenticatedUserUtil;
import com.happytrip.util.BeanFactory;
import com.happytrip.util.backup.AirlineBackup;
import com.happytrip.util.backup.RouteBackup;
import com.happytrip.util.transformer.AirlineModelTransformer;
import com.happytrip.util.transformer.RouteModelTransformer;

@Controller
public class AdminController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminController.class);
	private static final String LOCALE_MESSAGE = "Welcome home! the client locale is ";
	private AdminService adminService;
	private UserProfileService profileService;
	private BackupDao backupDao;

	
	public AdminController() {
		adminService = new AdminServiceImpl();
		profileService = new UserProfileServiceImpl();
		backupDao = new JdbcBackupDao();
		
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setProfileService(UserProfileService profileService) {
		this.profileService = profileService;
	}

	public void setBackupDao(BackupDao backupDao) {
		this.backupDao = backupDao;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@ModelAttribute("AddFlight")
	public Flight getAddFlightForm() {
		return new Flight();
	}

	@ModelAttribute("AddAirline")
	public Airline getAddAirlineForm() {
		return new Airline();
	}

	@ModelAttribute("AddRoute")
	public Route getAddRouteForm() {
		return new Route();
	}

	@ModelAttribute("AddSchedule")
	public FlightScheduleDto getAddScheduleForm() {
		return new FlightScheduleDto();
	}

	@RequestMapping(value = "/admin/addFlight.html", method = RequestMethod.GET)
	public String addFlight(Locale locale, Model model) {
		LOGGER.info(LOCALE_MESSAGE + locale.toString());

		List<Airline> airlines = listAllAirlines();
		List<FlightClass> classes = listAllFlightClasses();

		model.addAttribute("airlines", airlines);
		model.addAttribute("classes", classes);

		return "admin/addFlight";
	}

	List<Airline> listAllAirlines() {
		return adminService.getAllAirlines();
	}

	List<FlightClass> listAllFlightClasses() {
		return adminService.getAllFlightClasses();
	}

	@RequestMapping(value = "/admin/addAirline.html", method = RequestMethod.GET)
	public String addAirline(Locale locale, Model model) {
		LOGGER.info(LOCALE_MESSAGE + locale.toString());

		return "admin/addAirline";
	}

	@RequestMapping(value = "/admin/addAirline", method = RequestMethod.POST)
	public String saveAirline(@ModelAttribute("AddAirline") Airline airline,
			BindingResult result, Model model) {

		System.out.println("Admin Service"+adminService);
		if (!adminService.isDuplicateAirline(airline)) {
			adminService.addAirline(airline);
			model.addAttribute("message", "Airline added successfully");
			return "admin/resultSuccess";
		} else {
			User user = AuthenticatedUserUtil
					.currentLoggedInUser(profileService);
			AirlineDto airlineDto = AirlineModelTransformer.transform(airline);
			try {
				byte[] data = AirlineBackup.backup(airlineDto);
				Backup backup = new Backup();
				backup.setName("Airline");
				backup.setData(data);
				backupDao.save(backup);
			} catch (IOException e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage());
				return "admin/resultSuccess";
			}
		}

		return "admin/addAirline";
	}

	@RequestMapping(value = "/admin/addRoute.html", method = RequestMethod.GET)
	public String addRoute(Locale locale, Model model) {
		LOGGER.info(LOCALE_MESSAGE + locale.toString());

		List<City> cities = listAllCities();

		model.addAttribute("toCities", cities);
		model.addAttribute("fromCities", cities);

		return "admin/addRoute";
	}

	List<City> listAllCities() {
		return adminService.getAllCities();
	}

	@RequestMapping(value = "/admin/reports.html", method = RequestMethod.GET)
	public String showReports(Locale locale, Model model) {
		return "admin/reports";
	}

	@RequestMapping(value = "/admin/addRoute", method = RequestMethod.POST)
	public String saveRoute(@ModelAttribute("AddRoute") Route route,
			BindingResult result, Model model) {

		if (!adminService.isDuplicateRoute(route.getFromCity().getCityName(),
				route.getToCity().getCityName())) {
			route.setFromCity(adminService.getCityByName(route.getFromCity()
					.getCityName()));
			route.setToCity(adminService.getCityByName(route.getToCity()
					.getCityName()));
			adminService.addRoute(route);
			model.addAttribute("message", "Route added successfuly");
			return "admin/resultSuccess";
		} else {
			RouteDto routeDto = RouteModelTransformer.transform(route);
			try {
				byte[] data = RouteBackup.backup(routeDto);
				Backup backup = new Backup();
				backup.setName("Route");
				backup.setData(data);
				backupDao.save(backup);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.printStackTrace();
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage());
				return "admin/resultSuccess";
			}

		}

		List<City> cities = adminService.getAllCities();

		model.addAttribute("toCities", cities);
		model.addAttribute("fromCities", cities);
		model.addAttribute("message", "Route already added");
		return "admin/addRoute";
	}

	@RequestMapping(value = "/admin/scheduleFlight.html", method = RequestMethod.GET)
	public String addSchedule(Locale locale, Model model) {
		LOGGER.info(LOCALE_MESSAGE + locale.toString());

		List<Flight> flights = listAllFlights();
		List<Route> routes = listAllRoutes();
		List<FlightClass> classes = listAllFlightClasses();

		model.addAttribute("flights", flights);
		model.addAttribute("routes", routes);
		model.addAttribute("classes", classes);

		return "admin/scheduleFlight";
	}

	List<Flight> listAllFlights() {
		return adminService.getAllFlights();
	}

	List<Route> listAllRoutes() {
		return adminService.getAllRoutes();
	}

	@RequestMapping(value = "/admin/scheduleFlight", method = RequestMethod.POST)
	public String saveRoute(
			@ModelAttribute("AddSchedule") FlightScheduleDto flightScheduleDto,
			BindingResult result, Model model) {
		scheduleFlight(flightScheduleDto);
		model.addAttribute("message", "Schedule added successfuly");
		return "admin/resultSuccess";
	}

	void scheduleFlight(FlightScheduleDto flightScheduleDto) {
		ScheduledFlight scheduledFlight = flightScheduleDto
				.convertDtoToEntity();
		List<FlightCapacity> capacities = adminService
				.getCapacitiesForFlightId(scheduledFlight.getFlight()
						.getFlightId());
		adminService.setScheduleFlightDetails(flightScheduleDto, scheduledFlight, capacities);
		adminService.addFlightSchedule(scheduledFlight);
	}

}
