package com.portal.auto.register.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.portal.auto.bean.RegisterBean;

@Service 
public class UserValidation {

	private static final String USER_NULL = "Please enter User Name";
	private static final String USER_LENGTH = "User Name cannot be more than 50 Characters";
	
	private static final String CONFIRM_PASSWORD_NULL = "Please enter confirm password";
	private static final String PASSWORD_NULL = "Please enter password";
	private static final String PASSWORD_LENGTH = "Password cannot be more than 15 Characters";
	private static final String PASSWORD_CONFIRM_SAME = "Password and Confirm Password should be same";
	
	
	private boolean checkUserNameLength(String name, int length){
		if(name.trim().length() > length){
			return true;
		}
		return false;
	}
	
	private boolean checkPasswordLength(String password, int length){
		if(password.trim().length() > length){
			return true;
		}
		return false;
	}
	
	private boolean confirmPassword(String password, String confirmPassword){
		if(!password.trim().equals(confirmPassword.trim())){
			return true;
		} else {
			return false;
		}
	}
	
	
	public String validateUser(RegisterBean register){
		
		if(StringUtils.isBlank(register.getName())){
			return USER_NULL;
		} else if (StringUtils.isBlank(register.getPassword())){
			return PASSWORD_NULL;
		} else if(StringUtils.isBlank(register.getConfirmPassword())){
			return CONFIRM_PASSWORD_NULL;
		} else if(checkUserNameLength(register.getName(), 50)){
			return USER_LENGTH;
		} else if(checkPasswordLength(register.getPassword(),15)){
			return PASSWORD_LENGTH;
		} else if(confirmPassword(register.getPassword(),register.getConfirmPassword())){
			return PASSWORD_CONFIRM_SAME;
		}
		return null;
	}
}
