package com.happytrip.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.happytrip.model.User;
import com.happytrip.services.UserProfileService;

public final class AuthenticatedUserUtil {

	private AuthenticatedUserUtil(){}
	
	public static User currentLoggedInUser(UserProfileService profileService){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//get logged in username
	    String name = auth.getName(); 
	    
	    return profileService.getUserByUsername(name);
	}
}
