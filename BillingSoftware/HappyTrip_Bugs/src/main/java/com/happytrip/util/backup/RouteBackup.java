package com.happytrip.util.backup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.happytrip.controllers.dto.flight.RouteDto;

public final class RouteBackup {

	private RouteBackup(){}
	
	public static byte[] backup(RouteDto route) throws IOException {
		ByteArrayOutputStream buffer = null;
		ObjectOutputStream output = null;
		
		try {
			buffer = new ByteArrayOutputStream();
			output = new ObjectOutputStream(buffer);
			output.writeObject(route);
			return buffer.toByteArray();
		} finally{
			if(output != null) output.close();
			if(buffer != null) buffer.close();
		}
	}
}
