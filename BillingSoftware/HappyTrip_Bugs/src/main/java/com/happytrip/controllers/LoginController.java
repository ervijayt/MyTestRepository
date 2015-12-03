package com.happytrip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happytrip.model.User;

@Controller
public class LoginController {

	@ModelAttribute("UserLogin")
	public User getLoginForm() {
		return new User();
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/loginAdmin.html", method = RequestMethod.GET)
	public String loginAdmin(ModelMap model) {
		return "adminLogin";
	}
}
