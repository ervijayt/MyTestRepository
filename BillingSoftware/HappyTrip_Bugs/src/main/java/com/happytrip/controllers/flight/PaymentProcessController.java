package com.happytrip.controllers.flight;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class PaymentProcessController {
	
	@RequestMapping("/")
	public String hello() {
		return "PaymentDetails";
	}

	@RequestMapping(value = "/flight/bookSelectedFlights.html", method = RequestMethod.POST)
	public String paymentStatus(@RequestParam("name") String name, Model model) {
		String message = "Your payment is  " + name + "!";
		model.addAttribute("message", message);
		return "PaymentDetails";
	}


}
