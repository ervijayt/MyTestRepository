package com.happytrip.services.impl;

import java.util.Date;
import com.happytrip.dao.UserDao;
import com.happytrip.dao.jdbc.JdbcReportDao;
import com.happytrip.dao.jdbc.JdbcUserDao;
import com.happytrip.dao.report.ReportDao;
import com.happytrip.model.Role;
import com.happytrip.model.User;
import com.happytrip.model.reports.UserReport;
import com.happytrip.services.Roles;
import com.happytrip.services.UserProfileService;
import com.happytrip.util.BeanFactory;
import com.happytrip.util.transformer.UserModelTransformer;

public class UserProfileServiceImpl implements UserProfileService {

	private UserDao userDao;
	
	private ReportDao reportDao;
	
	public UserProfileServiceImpl() {
		userDao = new JdbcUserDao();
		reportDao = new JdbcReportDao();
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		Role userRole = new Role();
		userRole.setUser(user);
		userRole.setRole(Roles.ROLE_USER.name());
		user.addRole(userRole);
		user.setCreatedDate(new Date());
		user.setEnabled(true);
		userDao.save(user);
		UserReport usersReport = UserModelTransformer.transformToUserReport(user);
		reportDao.storeUserReport(usersReport);
	}

	@Override
	public User getUserByUsername(String username) {
		User  user = userDao.findForUsernameWithContactInfo(username);
		return  user == null?userDao.findForUsername(username):user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.userDao.update(user);
	}
	
	

}
