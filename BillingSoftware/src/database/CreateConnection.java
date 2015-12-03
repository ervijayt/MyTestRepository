package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {
	private Connection connectionInstance;
	public CreateConnection() {
		try {
			connectionInstance = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/sakila", "root","pass123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection GetConnection(){
		return connectionInstance;
	}

}
