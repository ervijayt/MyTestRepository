package com.happytrip.controllers.dto.flight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.happytrip.model.FlightRouteCost;
import com.happytrip.model.ScheduledFlight;

public class FlightScheduleDto {
	private ScheduledFlight scheduledFlight;
	private List<FlightRouteCost> ticketCosts = new ArrayList<FlightRouteCost>();
	public ScheduledFlight getScheduledFlight() {
		return scheduledFlight;
	}
	public void setScheduledFlight(ScheduledFlight scheduledFlight) {
		this.scheduledFlight = scheduledFlight;
	}
	public List<FlightRouteCost> getTicketCosts() {
		return ticketCosts;
	}
	public void setTicketCosts(List<FlightRouteCost> ticketCosts) {
		this.ticketCosts = ticketCosts;
	}
	
	public ScheduledFlight convertDtoToEntity(){
		for(FlightRouteCost cost:ticketCosts){
			cost.setFlightRoute(this.scheduledFlight);
		}
		this.scheduledFlight.setFlightRouteCosts(new HashSet<FlightRouteCost>(ticketCosts));
		return this.scheduledFlight;
	}
	
	

}
