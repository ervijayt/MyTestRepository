package com.happytrip.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.StateDao;
import com.happytrip.model.State;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;
public class JdbcStateDao implements StateDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStateDao.class);
	public JdbcStateDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(State state) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			
			String query = "insert into states(statename)" +
					" values(?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, state.getStateName());
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			cleanup(con, stmt, null);
		}
	}

}
