package com.happytrip.model;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the passengers database table.
 * 
 */
public class Passenger implements Serializable {
	private static final long serialVersionUID = 1L;

	private long passengerId;

	private String title;
		
	private Date dateOfBirth;

	private String gender;

	private String name;

	//bi-directional many-to-one association to Flightbooking
	private FlightBooking flightBooking;

    public Passenger() {
    }

	public long getPassengerId() {
		return this.passengerId;
	}

	public void setPassengerId(long passengerId) {
		this.passengerId = passengerId;
	}

	public Date getDateOfBirth() {
		if(this.dateOfBirth != null){
			return (Date)this.dateOfBirth.clone();
		}else{
			return dateOfBirth;
		}
	}

	public void setDateOfBirth(Date dateOfBirth) {
		if(this.dateOfBirth != null){
			this.dateOfBirth = (Date)dateOfBirth.clone();
			return;
		}
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FlightBooking getFlightBooking() {
		return this.flightBooking;
	}

	public void setFlightBooking(FlightBooking flightbooking) {
		this.flightBooking = flightbooking;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}