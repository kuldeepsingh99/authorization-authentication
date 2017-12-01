package com.portal.auto.register.dao;

import com.portal.auto.bean.UserBean;

public interface RegisterDAO {

	/**
	 * Register User
	 * @param user UserBean
	 * @return true:success, false: failure
	 */
	public boolean registerUser(UserBean user);
	
	/**
	 * Check if user exist
	 * @param user User Name
	 * @return true:User Exist, false: User does not exist
	 */
	public boolean checkUser(String user);
	
}
