package com.happytrip.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.happytrip.controllers.dto.UserDto;
import com.happytrip.dao.BackupDao;
import com.happytrip.dao.CityDao;
import com.happytrip.dao.jdbc.JdbcBackupDao;
import com.happytrip.dao.jdbc.JdbcCityDao;
import com.happytrip.services.BookingService;
import com.happytrip.services.UserProfileService;
import com.happytrip.services.impl.BookingServiceImpl;
import com.happytrip.services.impl.UserProfileServiceImpl;
import com.happytrip.util.BeanFactory;
import com.happytrip.util.transformer.UserDtoTransformer;

@Controller
public class RegisterController {
	
	
	private UserProfileService userProfileService;

	public RegisterController(){
		userProfileService = new UserProfileServiceImpl();
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    dateFormat.setLenient(false);

	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	   
	}
	
	@ModelAttribute("UserRegister")
	public UserDto getRegisterForm() {
		return new UserDto();
	}
	
	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public String register(ModelMap model) {

		return "register";
	}
	
	@RequestMapping(value = "/registerprocess", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute("UserRegister") UserDto userRegister,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "register";
		}
		userProfileService.createUser(UserDtoTransformer.transform(userRegister));
		return "login";
	}

}
