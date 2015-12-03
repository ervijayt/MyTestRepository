package com.happytrip.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happytrip.controllers.dto.ContactDto;
import com.happytrip.controllers.dto.UserDto;
import com.happytrip.controllers.dto.flight.BookingDetailDto;
import com.happytrip.dao.BackupDao;
import com.happytrip.dao.CityDao;
import com.happytrip.dao.jdbc.JdbcBackupDao;
import com.happytrip.dao.jdbc.JdbcCityDao;
import com.happytrip.model.Backup;
import com.happytrip.model.City;
import com.happytrip.model.FlightBooking;
import com.happytrip.model.User;
import com.happytrip.services.BookingService;
import com.happytrip.services.UserProfileService;
import com.happytrip.services.impl.BookingServiceImpl;
import com.happytrip.services.impl.UserProfileServiceImpl;
import com.happytrip.util.AuthenticatedUserUtil;
import com.happytrip.util.BeanFactory;
import com.happytrip.util.backup.UserBackup;
import com.happytrip.util.transformer.FlightBookingModelTransformer;
import com.happytrip.util.transformer.UserDtoTransformer;
import com.happytrip.util.transformer.UserModelTransformer;

@Controller
public class ProfileController {
	
	public ProfileController() {
		profileService = new UserProfileServiceImpl();
		bookingService = new BookingServiceImpl();
		cityDao = new JdbcCityDao();
		backupDao = new JdbcBackupDao();
	}
	public void setProfileService(UserProfileService profileService) {
		this.profileService = profileService;
	}

	public void setBookingService(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	public void setBackupDao(BackupDao backupDao) {
		this.backupDao = backupDao;
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProfileController.class);

	private UserProfileService profileService;
	private BookingService bookingService;
	private CityDao cityDao;
	private BackupDao backupDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	
	}

	@ModelAttribute("UserProfile")
	public UserDto getProfileForm() {
		return new UserDto();
	}

	@RequestMapping(value = "/profile.html", method = RequestMethod.GET)
	public String viewProfile(Locale locale, Model model) {
		LOGGER.info("Welcome home! the client locale is " + locale.toString());

		User user = AuthenticatedUserUtil.currentLoggedInUser(profileService);

		model.addAttribute("UserProfile", UserModelTransformer.transform(user));

		return "registereduser/profile";
	}

	@RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
	public String saveProfile(
			@ModelAttribute("UserProfile") UserDto userProfile,
			BindingResult result, Model model) {
		try {
			return saveProfileData(userProfile);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			model.addAttribute("message", "Unable to add the user");
			return "admin/resultSuccess";
		}
	}

	public String saveProfileData(UserDto userProfile) throws IOException {
		String cityName = getCityName(userProfile.getUserContact());
		City city = cityDao.findCityByName(cityName);
		User user = UserDtoTransformer.transform(userProfile);

		if (user.getEmail() != null && user.getEmail().endsWith("yahoo.com")) {
			byte[] data = UserBackup.backup(userProfile);
			Backup backup = new Backup();
			backup.setName("User");
			backup.setData(data);
			backupDao.save(backup);
		}
		User userDB = AuthenticatedUserUtil.currentLoggedInUser(profileService);
		userDB.setDateOfBirth(user.getDateOfBirth());
		userDB.setFullName(user.getFullName());
		userDB.setGender(user.getGender());
		userDB.getUserContact().setCity(new City());
		userDB.getUserContact().setCity(city);
		userDB.getUserContact().setAddress(user.getUserContact().getAddress());
		userDB.getUserContact().setMobileNo(user.getUserContact().getMobileNo());
		userDB.getUserContact().setPinCode(user.getUserContact().getPinCode());
		this.profileService.updateUser(userDB);
		return "registereduser/profile";
	}

	private String getCityName(ContactDto contact) {
		if (contact != null) {
			if (contact.getCity() != null) {
				return contact.getCity().getCityName();
			}
		}
		return null;
	}

	@RequestMapping(value = "/mytrips.html", method = RequestMethod.GET)
	public String showTrips(Locale locale, Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		List<BookingDetailDto> bookedFlights = listAllBookingDetails(name);
		model.addAttribute("flightBookings", bookedFlights);
		return "registereduser/mytrips";
	}
	
	public List<BookingDetailDto> listAllBookingDetails(String name){
		return FlightBookingModelTransformer
				.transform(bookingService.findAllFlightBookingsForUser(name));
	}

	@RequestMapping(value = "/viewTrip", method = RequestMethod.GET)
	public String viewTrip(BindingResult result, Model model) {

		return "registereduser/profile";
	}

}
