package com.happytrip.dao;

import java.util.List;

import com.happytrip.model.City;

public interface CityDao {
	void save(City city);
	List<City> getAllCities();
	City findCityByName(String name);
	void removeAll();
}
