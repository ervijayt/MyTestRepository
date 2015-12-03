package com.happytrip.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.happytrip.model.User;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"email", "required.email",
				"Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Field name is required.");

	}

}