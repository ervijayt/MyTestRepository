package com.happytrip.dao;

import com.happytrip.model.User;

public interface UserDao {

	void save(User user);
	User findForEmail(String email);
	User findForUsername(String username);
	void update(User user);
	User findForUsernameWithContactInfo(String username);
}
