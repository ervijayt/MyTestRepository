package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.UserDao;
import com.happytrip.model.Role;
import com.happytrip.model.User;
import com.happytrip.model.UserContact;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;

public class JdbcUserDao implements UserDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JdbcUserDao.class);

	public JdbcUserDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(User user) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);

			String query = "insert into USERS(CREATEDDATE, DATEOFBIRTH, FULLNAME, GENDER, LOGINID, USERNAME, PASSWORD, ENABLED) values(?, ?, ?, ?, ?, ?, ?, ?)";
			String query1 = "insert into USER_ROLES(USER_ID, AUTHORITY) values(?, ?)";
			stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getCreatedDate().toLocaleString());
			stmt.setString(2, user.getDateOfBirth().toLocaleString());
			stmt.setString(3, user.getFullName());
			stmt.setString(4, user.getGender());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getEmail());
			stmt.setString(7, user.getPassword());
			stmt.setBoolean(8, user.isEnabled());
			stmt.executeUpdate();
			ResultSet generatedSet = stmt.getGeneratedKeys();
			generatedSet.next();
			int userId = generatedSet.getInt(1);
			stmt = con.prepareStatement(query1);
			for (Role role : user.getRoles()) {
				stmt.setInt(1, userId);
				stmt.setString(2, role.getRole());
				stmt.addBatch();
			}
			int[] values = stmt.executeBatch();
			con.commit();
			System.out.println("state" + values[0]);
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, null);
		}

	}

	@Override
	public User findForEmail(String email) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "select * from users u where u.username=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, email);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				User user = new User();
				user.setCreatedDate(new Date(result.getString(2)));

			}
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, null);
		}
		return null;

	}

	public User findForUsername(String username) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "select * from users u where u.username=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			User user = null;
			while (result.next()) {
				user = new User();
				user.setUserId(result.getLong("user_id"));
				user.setPassword(result.getString("password"));
				user.setFullName(result.getString("fullname"));
				user.setEmail(username);
				UserContact userContact = new UserContact();
				user.setUserContact(userContact);
				
			}
			con.close();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, null);
		}

	}
	@Override
	public User findForUsernameWithContactInfo(String username) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "select * from users u,usercontactinformation ur where u.user_id = ur.user_id and u.username=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			User user = null;
			while (result.next()) {
				user = new User();
				user.setUserId(result.getLong("user_id"));
				user.setPassword(result.getString("password"));
				user.setEmail(username);
				UserContact userContact = new UserContact();
				userContact.setUserId(result.getLong("contactid"));
				user.setUserContact(userContact);
			}
			con.close();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, null);
		}
	}

	@Override
	public void update(User user) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getMySqlConnection();
			con.setAutoCommit(false);
			String query = "UPDATE users SET `username`='"+user.getEmail()+"', `fullname`='"+user.getFullName()+"', `gender`='"+user.getGender()+"', `password`='"+user.getPassword()+"' WHERE `user_id`="+user.getUserId()+";";
			String query1 = null;
			if(user.getUserContact().getUserId() == 0){
				query1 = "INSERT INTO `happytrip`.`usercontactinformation` (`address`, `mobileno`, `pincode`, `cityid`, `user_id`) VALUES ('"+user.getUserContact().getAddress()+"','"+user.getUserContact().getMobileNo()+"','"+user.getUserContact().getPinCode()+"',"+user.getUserContact().getCity().getCityId()+","+user.getUserId()+")";	
			}else{
				query1 = "UPDATE `happytrip`.`usercontactinformation` SET `address`='"+user.getUserContact().getAddress()+"', `mobileno`='"+user.getUserContact().getMobileNo()+"', `pincode`='"+user.getUserContact().getPinCode()+"', `cityid`="+user.getUserContact().getCity().getCityId()+" WHERE `contactid`='"+user.getUserContact().getUserId()+"'";	
			}
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.executeUpdate(query1);
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}
	}
}
