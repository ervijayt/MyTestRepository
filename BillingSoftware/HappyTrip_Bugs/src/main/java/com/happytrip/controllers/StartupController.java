package com.happytrip.controllers;

import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.happytrip.dao.CityDao;
import com.happytrip.dao.FlightClassDao;
import com.happytrip.dao.GenericDao;
import com.happytrip.dao.LookupDao;
import com.happytrip.dao.StateDao;
import com.happytrip.dao.UserDao;
import com.happytrip.dao.jdbc.JdbcCityDao;
import com.happytrip.dao.jdbc.JdbcFlightClassDao;
import com.happytrip.dao.jdbc.JdbcGenericDao;
import com.happytrip.dao.jdbc.JdbcLookupDao;
import com.happytrip.dao.jdbc.JdbcStateDao;
import com.happytrip.dao.jdbc.JdbcUserDao;
import com.happytrip.model.City;
import com.happytrip.model.FlightClass;
import com.happytrip.model.Role;
import com.happytrip.model.State;
import com.happytrip.model.User;
import com.happytrip.model.UserContact;
import com.happytrip.services.FlightBookingService;
import com.happytrip.util.BeanFactory;
import com.happytrip.util.StringUtil;

public class StartupController extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(StartupController.class);

	private UserDao userDao;

	private CityDao cityDao;

	private LookupDao lookupDao;

	private StateDao stateDao;

	private FlightClassDao flightClassDao;

	private GenericDao genericDao;
	private static SessionFactory sFactory = null;

	public static void setSessionFactor(SessionFactory sessionFactory) {
		sFactory = sessionFactory;
	}


	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		initDependencies();
		Properties props;
		LOGGER.info("Initializing app");
		try {
			props = StringUtil
					.getPropertiesFromClasspath("data/data.properties");
			String initValue = props.getProperty("reinit");
			// TODO Auto-generated method stub
			LOGGER.info("Init value is " + initValue);
			if (initValue.equals("false")) {
				return;
			}

			initCities(props);
			initFlightClasses(props);
			initUsers(props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initDependencies() {
		// TODO Auto-generated method stub
		lookupDao = new JdbcLookupDao();
		cityDao = new JdbcCityDao();
		stateDao = new JdbcStateDao();
		flightClassDao = new JdbcFlightClassDao();
		userDao = new JdbcUserDao();
		genericDao = new JdbcGenericDao();
	}

	private void initCities(Properties props) {

		String cities = props.getProperty("cities");
		StringTokenizer tokens = new StringTokenizer(cities, ",");
		while (tokens.hasMoreTokens()) {
			String cityVal = tokens.nextToken();

			String[] cityValues = cityVal.split(":");
			String cityName = cityValues[0];
			String stateName = cityValues[1];

			if (lookupDao.findForCityName(cityName) == null) {
				State state = addOrGetState(stateName);
				City city = new City();
				city.setCityName(cityName);
				city.setState(state);
				LOGGER.info("Saving city " + cityName);
				cityDao.save(city);
			}
		}
	}

	private State addOrGetState(String stateName) {
		// TODO Auto-generated method stub
		State state = lookupDao.findForStateName(stateName);
		if (state != null) {
			LOGGER.info("Returning state " + stateName);
			return state;
		} else {
			state = new State();
			state.setStateName(stateName);
			LOGGER.info("Saving state " + stateName);
			stateDao.save(state);
			return state;
		}
	}

	private void initFlightClasses(Properties props) {
		String classes = props.getProperty("classes");
		StringTokenizer tokens = new StringTokenizer(classes, ",");

		while (tokens.hasMoreTokens()) {
			String className = tokens.nextToken();
			if (lookupDao.findForClassName(className) == null) {
				FlightClass flightClass = new FlightClass();
				flightClass.setClassType(className);
				flightClassDao.save(flightClass);
			}
		}
	}

	private void initUsers(Properties props) {
		// TODO Auto-generated method stub
		String users = props.getProperty("users");
		StringTokenizer tokens = new StringTokenizer(users, ",");

		while (tokens.hasMoreTokens()) {
			String userVal = tokens.nextToken();
			String[] userValues = userVal.split(":");
			String userName = userValues[0];
			String password = userValues[1];
			String roleName = userValues[2];
			if (userDao.findForUsername(userName) == null) {
				User user = new User();
				user.setEmail(userName);
				user.setPassword(password);
				user.setEnabled(true);
				UserContact contact = new UserContact();
				contact.setUser(user);
				user.setUserContact(contact);
				Role role = new Role();				
				role.setRole(roleName);
				role.setUser(user);
				user.addRole(role);
				userDao.save(user);
			}
		}
	}

}
