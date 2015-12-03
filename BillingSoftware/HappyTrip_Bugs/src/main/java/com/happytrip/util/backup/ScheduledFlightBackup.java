package com.happytrip.util.backup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.happytrip.controllers.dto.flight.ScheduledFlightDto;

public final class ScheduledFlightBackup {

	private ScheduledFlightBackup(){}
	
	public static byte[] backup(ScheduledFlightDto scheduledFlight) throws IOException {
		ByteArrayOutputStream buffer = null;
		ObjectOutputStream output = null;
		
		try {
			buffer = new ByteArrayOutputStream();
			output = new ObjectOutputStream(buffer);
			output.writeObject(scheduledFlight);
			return buffer.toByteArray();
		} finally{
			if(output != null) output.close();
			if(buffer != null) buffer.close();
		}
	}
}
