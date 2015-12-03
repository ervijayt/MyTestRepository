package com.happytrip.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public final class StringUtil {

	private StringUtil(){}


	public static long generateReference(long seed){
		Random random = new Random(seed);
		long val = random.nextLong();
		if(val<0){
			return val * -1;
		}
		return val;
	}

	public static Properties getPropertiesFromClasspath(String propFileName) throws IOException {
		// loading xmlProfileGen.properties from the classpath
		Properties props = new Properties();
		InputStream inputStream = StringUtil.class.getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream == null) {
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

		props.load(inputStream);

		return props;
	}

	public static StringBuffer getAboutUsPageContent(String fileName) throws IOException
	{
		byte[] byteArray = new byte[200];
		InputStream inputStream =  getStream(byteArray, fileName);
		StringBuffer stringBuffer = new StringBuffer();
		int numOfBytesRead = 0 ;
		while((numOfBytesRead = inputStream.read(byteArray)) > 0 )
		{
			stringBuffer = stringBuffer.append(String.valueOf(byteArray));
		}
		return stringBuffer;
	}

	private static InputStream getStream(byte[] byteArray,String fileName) throws IOException
	{
		InputStream inputStream = (InputStream) StringUtil.class.getClassLoader()
				.getResourceAsStream(fileName);

		return inputStream;

	}

	public static StringBuffer getExternalAdvertisementPageContent(String fileName) throws IOException
	{
		byte[] byteArray = new byte[20];
		InputStream inputStream =  getStream(byteArray, fileName);
		StringBuffer stringBuffer = new StringBuffer();
		int numOfBytesRead = 0 ;
		while((numOfBytesRead = inputStream.read(byteArray)) > 0 )
		{
			stringBuffer = stringBuffer.append(new String(byteArray));
		}
		return stringBuffer;
	}
}
