package com.portal.auto.service;

import com.portal.auto.bean.UserBean;

public interface LoginService {

	/**
	 * Finds the User Object by name
	 * @param userName User Name
	 * @return UserBean
	 */
	UserBean findByUserName(String userName);
	
	/**
	 * Updates Token 
	 * @param userBean User Object
	 * @param token token
	 * @param userType User Type (WEB, ANDRIOD, IOS)
	 * @return true:success, false: failure
	 */
	boolean updateToken(UserBean userBean,String token, String userType);
	
}
