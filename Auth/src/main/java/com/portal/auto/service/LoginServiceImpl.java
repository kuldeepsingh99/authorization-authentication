package com.portal.auto.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.auto.bean.UserBean;
import com.portal.auto.bean.UserToken;
import com.portal.auto.dao.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	public LoginDAO loginDAO;
	
	public UserBean findByUserName(String userName) {
		return loginDAO.findByUserName(userName);
	}

	public boolean updateToken(UserBean userBean,String userToken, String userType) {
		Set<UserToken> type = userBean.getUserToken();
		for(UserToken token: type){
			if(token.getUserType().equalsIgnoreCase(userType)){
				token.setToken(userToken);
			}
		}
		return loginDAO.updateToken(userBean);
	}
}
