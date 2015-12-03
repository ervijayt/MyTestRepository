package com.happytrip.controllers.dto.flight;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.happytrip.model.City;

public class RouteDto {

	private InputStream stream = new ByteArrayInputStream(new byte[10]);

	private long routeId;

	private City toCity;

	private City fromCity;

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream userStream) {
		this.stream = userStream;
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public City getToCity() {
		return toCity;
	}

	public void setToCity(City toCity) {
		this.toCity = toCity;
	}

	public City getFromCity() {
		return fromCity;
	}

	public void setFromCity(City fromCity) {
		this.fromCity = fromCity;
	}
}
