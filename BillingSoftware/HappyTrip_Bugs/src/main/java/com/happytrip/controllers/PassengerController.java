package com.happytrip.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PassengerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PassengerController.class);

	@RequestMapping(value = "/passengerFlights.html", method = RequestMethod.GET)
	public String passengerFlights(Locale locale, Model model) {
		LOGGER.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "flight/flight_passengers";
	}

}
