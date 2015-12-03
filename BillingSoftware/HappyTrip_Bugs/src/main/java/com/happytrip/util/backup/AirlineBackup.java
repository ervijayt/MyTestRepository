package com.happytrip.util.backup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.happytrip.controllers.dto.flight.AirlineDto;

public final class AirlineBackup {

	private AirlineBackup(){}
	
	public static byte[] backup(AirlineDto airlineDto) throws IOException {
		ByteArrayOutputStream buffer = null;
		ObjectOutputStream output = null;
		
		try {
			buffer = new ByteArrayOutputStream();
			output = new ObjectOutputStream(buffer);
			output.writeObject(airlineDto);
			return buffer.toByteArray();
		} finally{
			if(output != null) output.close();
			if(buffer != null) buffer.close();
		}
	}
}
