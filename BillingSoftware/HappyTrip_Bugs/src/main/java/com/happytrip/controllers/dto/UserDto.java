package com.happytrip.controllers.dto;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.util.Date;

public class UserDto{
	private InputStream userStream = new ByteArrayInputStream(new byte[10]);
	private long userId;
	private Date createdDate;
	private Date dateOfBirth;
	private String fullName;
	private String gender;
	private String loginId;
	private String email;
	private String password;
	private boolean enabled;
	private ContactDto userContact;

	public UserDto() {
		createdDate = new Date();
		dateOfBirth = new Date();
	}
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public ContactDto getUserContact() {
		return userContact;
	}

	public void setUserContact(ContactDto userContact) {
		this.userContact = userContact;
	}

	public InputStream getUserStream() {
		return userStream;
	}

	public void setUserStream(InputStream userStream) {
		this.userStream = userStream;
	}
}
