package com.happytrip.dao.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.happytrip.dao.BackupDao;
import com.happytrip.model.Backup;

import static com.happytrip.dao.jdbc.ConnectionHelper.*;

public class JdbcBackupDao implements BackupDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcBackupDao.class);

	public JdbcBackupDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Backup backup) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			
			String query = "insert into backup(name)" +
					" values(?)";
			stmt = con.prepareStatement(query);
			stmt.setString(0, backup.getName());
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
