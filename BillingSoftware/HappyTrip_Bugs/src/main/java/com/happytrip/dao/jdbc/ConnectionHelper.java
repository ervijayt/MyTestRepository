package com.happytrip.dao.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.happytrip.util.StringUtil;

public final class ConnectionHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHelper.class);

	public static Connection getMySqlConnection () throws ClassNotFoundException, SQLException, IOException
	{
		Properties props = StringUtil.getPropertiesFromClasspath("data/data.properties");
		//Load the driver
		String driverName= props.getProperty("jdbc.driver.name");
		Class.forName(driverName);
		
		//To get the connection we need url, user, password
		String url=props.getProperty("jdbc.url");
		String user=props.getProperty("jdbc.username");
		String pwd=props.getProperty("jdbc.password");
		
		Connection conn=DriverManager.getConnection(url, user, pwd);
		return conn;
		
	}
	
	public static void cleanup(Connection conn, Statement stmt, ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}
	
	public static void cleanup(Connection conn, PreparedStatement stmt, ResultSet rs){
		try{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}
}
