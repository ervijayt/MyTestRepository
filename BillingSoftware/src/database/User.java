package database;
import java.sql.*;
import Model.*;
public class User {
	Model.User[] userInstance;
	Connection dbConnection;
	User(){
		dbConnection = new CreateConnection().GetConnection();
	}
	Model.User[] GetAllUsers(){
		return userInstance;
	}
}
