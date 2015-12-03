package com.happytrip.util.backup;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;

import com.happytrip.controllers.dto.UserDto;

public final class UserBackup {

	private UserBackup() {
	}

	public static byte[] backup(UserDto user) throws IOException {
		ByteArrayOutputStream buffer = null;
		ObjectOutputStream output = null;
		
		try {
			buffer = new ByteArrayOutputStream();
			output = new ObjectOutputStream(buffer);
			output.writeObject(user);
			return buffer.toByteArray();
		} finally{
			if(output != null) output.close();
			if(buffer != null) buffer.close();
		}
	}
}
