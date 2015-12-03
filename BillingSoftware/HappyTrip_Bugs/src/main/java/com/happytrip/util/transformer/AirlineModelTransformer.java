package com.happytrip.util.transformer;

import com.happytrip.controllers.dto.flight.AirlineDto;
import com.happytrip.model.Airline;
import com.happytrip.model.reports.AirlineReport;

public final class AirlineModelTransformer {

	private AirlineModelTransformer(){}
	
	public static AirlineDto transform(Airline airline){
		AirlineDto airlineDto = new AirlineDto();
		airlineDto.setAirlineCode(airline.getAirlineCode());
		airlineDto.setAirlineId(airline.getAirlineId());
		airlineDto.setAirlineLogo(airline.getAirlineLogo());
		airlineDto.setAirlineName(airline.getAirlineName());
		airlineDto.setFlights(airline.getFlights());
		return airlineDto;
	}
	
	public static AirlineReport transformToAirlineReport(Airline airline)
	{
		AirlineReport airlineReport = new AirlineReport();
		airlineReport.setAirlineCode(airline.getAirlineCode());
		airlineReport.setAirlineLogo(airline.getAirlineLogo());
		airlineReport.setAirlineName(airline.getAirlineName());
		return airlineReport;
	}
}
