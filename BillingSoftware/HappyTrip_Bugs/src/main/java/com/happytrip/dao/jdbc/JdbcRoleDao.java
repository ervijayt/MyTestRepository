package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.RoleDao;
import com.happytrip.model.Role;
import com.happytrip.model.User;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;
public class JdbcRoleDao implements RoleDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcRoleDao.class);
	public JdbcRoleDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Role getRoleForUsername(String username) {
		String routeQuery = "select * from user_roles where user_id=(select user_id from users where username=?)";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement(routeQuery);
			Role role = new Role();
			User user = new User();
			stmt.setString(1,username);
			result = stmt.executeQuery();
			result.next();
			role.setId(result.getLong(1));
			role.setRole(result.getString(2));
			user.setUserId(result.getLong(3));
			role.setUser(user);
			con.close();
			return role;
		}catch(Exception e)
		{
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, result);
		}
	}

}
