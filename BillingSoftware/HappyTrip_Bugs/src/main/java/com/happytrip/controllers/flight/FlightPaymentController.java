package com.happytrip.controllers.flight;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.happytrip.controllers.dto.flight.FlightBookingDto;
import com.happytrip.controllers.dto.flight.PassengerListDto;
import com.happytrip.dao.FlightRouteDao;
import com.happytrip.model.Passenger;
import com.happytrip.services.FlightBookingService;
import com.happytrip.services.impl.FlightBookingServiceImpl;
import com.happytrip.util.BeanFactory;

@Controller
@SessionAttributes({"SearchData","FlightSelected","PassengersList","Bookings"})
public class FlightPaymentController {

	private FlightBookingService flightBookingService;
	
	public FlightPaymentController() {
		flightBookingService = new FlightBookingServiceImpl();
	}
	
	@RequestMapping(value = "/flight/confirmBooking.html", method = RequestMethod.POST)
	public ModelAndView confirmBooking(
			@ModelAttribute("PassengersList") PassengerListDto passengerList,
			@ModelAttribute("Bookings") FlightBookingDto flightBooking){
		flightBookingService.bookFlight(flightBooking.getOutboundFlightBooking());
		
		
		Map<String, Object> models = new HashMap<String, Object>();
		Passenger primaryPassenger = passengerList.getPassengers().get(0);
		models.put("PrimaryPassenger", primaryPassenger);
		
		if(flightBooking.getReturnFlightBooking() != null){
			flightBookingService.bookFlight(flightBooking.getReturnFlightBooking());
		}
		return new ModelAndView("/flight/bookingConfirmation",models);
	}
}
