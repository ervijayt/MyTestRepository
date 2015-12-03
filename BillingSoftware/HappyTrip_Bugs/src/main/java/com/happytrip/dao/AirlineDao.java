package com.happytrip.dao;

import java.util.List;

import com.happytrip.model.Airline;

public interface AirlineDao {
	void save(Airline airline);
	Airline findByAirlineCode(String code);
	List<Airline> findAll();
}
