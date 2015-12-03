package com.happytrip.services;

import com.happytrip.model.User;

public interface UserProfileService {

	/**
	 * Create a new user
	 * @param user
	 */
	void createUser(User user);
	
	/**
	 * Find a user based on the user name
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	/**
	 * Update an existing user
	 * @param user
	 */
	void updateUser(User user);
}
