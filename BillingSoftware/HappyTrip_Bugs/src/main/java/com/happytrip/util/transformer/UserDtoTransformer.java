package com.happytrip.util.transformer;

import com.happytrip.controllers.dto.ContactDto;
import com.happytrip.controllers.dto.UserDto;
import com.happytrip.model.User;
import com.happytrip.model.UserContact;

public final class UserDtoTransformer {

	private UserDtoTransformer() {
	}

	public static User transform(UserDto userDto) {
		User user = new User();
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setEmail(userDto.getEmail());
		user.setEnabled(userDto.isEnabled());
		user.setLoginId(userDto.getLoginId());
		user.setPassword(userDto.getPassword());
		user.setUserId(userDto.getUserId());
		user.setGender(userDto.getGender());
		user.setFullName(userDto.getFullName());
		
		
		
		UserContact contact = transform(userDto.getUserContact());
		if (contact != null) {
			contact.setUser(user);
			user.setUserContact(contact);
		}
		return user;
	}

	public static UserContact transform(ContactDto contactDto) {
		if (contactDto != null) {
			UserContact contact = new UserContact();
			contact.setUserId(contactDto.getUserId());
			contact.setAddress(contactDto.getAddress());
			contact.setMobileNo(contactDto.getMobileNo());
			contact.setPinCode(contactDto.getPinCode());
			contact.setCity(contactDto.getCity());
			return contact;
		} else {
			return null;
		}
	}
}
