package com.happytrip.dao;

import java.util.List;

import com.happytrip.model.City;
import com.happytrip.model.FlightClass;
import com.happytrip.model.Role;
import com.happytrip.model.State;

public interface LookupDao {

	FlightClass findForClassName(String className);
	List<FlightClass> findAllFlightClasses();
	State findForStateName(String stateName);
	Role findForRoleName(String roleName);
	City findForCityName(String cityName);
}
