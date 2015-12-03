package com.happytrip.util.transformer;

import java.util.ArrayList;
import java.util.List;

import com.happytrip.controllers.dto.flight.BookingDetailDto;
import com.happytrip.model.Flight;
import com.happytrip.model.FlightBooking;
import com.happytrip.model.reports.BookingReport;

public final class FlightBookingModelTransformer {

	private FlightBookingModelTransformer(){}
	
	public static List<BookingDetailDto> transform(List<FlightBooking> bookings){
		List<BookingDetailDto> bookingDetails = new ArrayList<BookingDetailDto>();
		for(FlightBooking booking:bookings){
			bookingDetails.add(transform(booking));
		}
		return bookingDetails;
	}
	
	public static BookingDetailDto transform(FlightBooking booking){
		BookingDetailDto bookingDetails = new BookingDetailDto();
		bookingDetails.setBooking(booking.getBooking());
		bookingDetails.setBookingId(booking.getBookingId());
		bookingDetails.setCostPerTicket(booking.getCostPerTicket());
		bookingDetails.setFlightClass(booking.getFlightClass());
		bookingDetails.setFlightRoute(booking.getFlightRoute());
		bookingDetails.setNoOfSeats(booking.getNoOfSeats());
		bookingDetails.setPassengers(booking.getPassengers());
		return bookingDetails;
	}
	
	public static BookingReport transformToBookingReport(FlightBooking flightBooking)
	{
		BookingReport bookingReport = new BookingReport();
		bookingReport.setCostPerTicket(flightBooking.getCostPerTicket());
		bookingReport.setDateOfJourney(flightBooking.getDateOfJourney());
		bookingReport.setNumberOfSeats(flightBooking.getNoOfSeats());
		return bookingReport;
	}
}
