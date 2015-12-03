package com.happytrip.model;

import java.io.Serializable;


public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private User user;
	
	private String role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
