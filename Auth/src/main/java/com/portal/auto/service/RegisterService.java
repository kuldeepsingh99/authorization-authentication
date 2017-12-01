package com.portal.auto.service;

import com.portal.auto.bean.RegisterBean;
import com.portal.auto.bean.ResponseBean;

public interface RegisterService {

	/**
	 * Register User
	 * @param register RegisterBean
	 * @return
	 */
	ResponseBean registerUser(RegisterBean register);
}
