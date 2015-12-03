package com.happytrip.util.backup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.happytrip.controllers.dto.flight.BookingDetailDto;

public final class FlightBookingBackup {

	private FlightBookingBackup(){}
	
	public static byte[] backup(BookingDetailDto booking) throws IOException {
		ByteArrayOutputStream buffer = null;
		ObjectOutputStream output = null;
		
		try {
			buffer = new ByteArrayOutputStream();
			output = new ObjectOutputStream(buffer);
			output.writeObject(booking);
			return buffer.toByteArray();
		} finally{
			if(output != null) output.close();
			if(buffer != null) buffer.close();
		}
	}
}
