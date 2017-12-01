package com.portal.auto.dao;

import com.portal.auto.bean.UserBean;

public interface LoginDAO {

	/**
	 * Finds the User Object by name
	 * @param userName User Name
	 * @return UserBean
	 */
	UserBean findByUserName(String userName);
	
	/**
	 * Updates Token 
	 * @param userBean User Object
	 * @return true:success, false: failure
	 */
	boolean updateToken(UserBean userBean);
	
}
