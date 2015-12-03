package com.happytrip.controllers.dto.flight;


import com.happytrip.model.ScheduledFlight;

public class FlightSelectionDto {
	
	private ScheduledFlight selectedOutboundFlight;
	private ScheduledFlight selectedReturnFlight;
	
	public void setSelectedOutboundFlight(ScheduledFlight selectedOutboundFlight) {
		this.selectedOutboundFlight = selectedOutboundFlight;
	}

	public void setSelectedReturnFlight(ScheduledFlight selectedReturnFlight) {
		this.selectedReturnFlight = selectedReturnFlight;
	}

	public ScheduledFlight getSelectedOutboundFlight(){
		return this.selectedOutboundFlight;
	}
	
	public ScheduledFlight getSelectedReturnFlight(){
		return this.selectedReturnFlight;
	}
}
