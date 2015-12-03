package com.happytrip.controllers.flight;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.happytrip.controllers.dto.flight.FlightSearchDto;
import com.happytrip.controllers.dto.flight.FlightSelectionDto;
import com.happytrip.controllers.dto.flight.PassengerListDto;
import com.happytrip.dao.FlightRouteDao;
import com.happytrip.dao.LookupDao;
import com.happytrip.dao.jdbc.JdbcFlightRouteDao;
import com.happytrip.model.Passenger;
import com.happytrip.model.ScheduledFlight;
import com.happytrip.model.User;
import com.happytrip.services.UserProfileService;
import com.happytrip.services.impl.UserProfileServiceImpl;
import com.happytrip.util.AuthenticatedUserUtil;
import com.happytrip.util.BeanFactory;

@Controller
@SessionAttributes({"SearchData","SelectedFlights","PassengerList","FlightSelected","PassengersList"})
public class FlightBookingController {

	private UserProfileService profileService;
	private FlightRouteDao flightRouteDao;

	public FlightBookingController() {
		  flightRouteDao = new JdbcFlightRouteDao();
			profileService = new UserProfileServiceImpl();

	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	  
	}
	
	@ModelAttribute("PassengerList")
	public PassengerListDto getPassengerList(){
		User user = AuthenticatedUserUtil.currentLoggedInUser(profileService);
		PassengerListDto listOfPassengers = new PassengerListDto();
		if(user != null){
			Passenger passenger = new Passenger();
			passenger.setDateOfBirth(user.getDateOfBirth());
			passenger.setName(user.getFullName());
			listOfPassengers.getPassengers().add(0, passenger);
		}
		return listOfPassengers;
	}
	
	@RequestMapping(value = "/flight/bookSelectedFlights.html", method = RequestMethod.POST)
	public String bookSelectedFlights(@ModelAttribute("PassengerList")PassengerListDto passengerList,
			@ModelAttribute("SelectedFlights") FlightSelectionDto selectedFlights,
			@ModelAttribute("SearchData") FlightSearchDto searchData,Model model){
		ScheduledFlight selectedOutbound = selectedFlights.getSelectedOutboundFlight();
		ScheduledFlight selectedReturn = selectedFlights.getSelectedReturnFlight();
		model.addAttribute("FlightSelected", selectedFlights);
		model.addAttribute("PassengersList", passengerList);
		if(selectedOutbound != null){
			selectedFlights.setSelectedOutboundFlight(flightRouteDao.findDetailsById(
					selectedOutbound.getFlightRouteId(), searchData.getFlightClass()));
		}
		if(selectedReturn != null){
			selectedFlights.setSelectedReturnFlight(flightRouteDao.findDetailsById(
					selectedReturn.getFlightRouteId(), searchData.getFlightClass()));
		}
		
		return "/flight/addPassengers";
	}

}
