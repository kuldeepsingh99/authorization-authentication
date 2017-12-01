package com.portal.auto.controller;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portal.auto.bean.LoginCredentials;
import com.portal.auto.bean.ResponseBean;
import com.portal.auto.bean.UserBean;
import com.portal.auto.bean.UserToken;
import com.portal.auto.exception.FailedToLoginException;
import com.portal.auto.exception.InvalidTokenException;
import com.portal.auto.security.JwtService;
import com.portal.auto.service.LoginService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
public class JWTRefreshController {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(path = "/refresh", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseBean login(@RequestBody LoginCredentials credentials, HttpServletResponse response) {

		ResponseBean responseBean = new ResponseBean();
		String newToken = null;
		if(StringUtils.isNotBlank(credentials.getToken()) && StringUtils.isNotBlank(credentials.getUsername())){
			
			UserBean user = loginService.findByUserName(credentials.getUsername());
	    	
			if(null == user){
				responseBean.setFlag("failure");
				responseBean.setMessage("User not found");
	    		throw new FailedToLoginException(credentials.getUsername());
	    	}
			
			Set<UserToken> tokenSet = user.getUserToken();
			Iterator<UserToken> it = tokenSet.iterator();
			while(it.hasNext()){
				UserToken userToken = it.next();
				if(userToken.getUserType().equals(credentials.getUserType())){
					if(!userToken.getToken().equals(credentials.getToken())){
						responseBean.setFlag("failure");
						responseBean.setMessage("Invalid Token");
						throw new InvalidTokenException(credentials.getUsername());
					}
				}
			}
			
					
	    	try {
				Jws<Claims> claim = jwtService.verify(credentials.getToken());
				String userName = claim.getBody().getSubject();
				
				if(null != userName && !userName.equals(credentials.getUsername())){
					responseBean.setFlag("failure");
					responseBean.setMessage("User name not correct");
					throw new FailedToLoginException(credentials.getUsername());
				}
				
			} catch (Exception e) {
				// Token expires, now issue a new Token
				try {
					newToken = jwtService.tokenFor(credentials.getUsername(), user.getRole());
					loginService.updateToken(user, newToken,credentials.getUserType());
				} catch (Exception e1) {
					throw new FailedToLoginException(credentials.getUsername());
				}
				responseBean.setRole(user.getRole());
				responseBean.setFlag("success");
				responseBean.setMessage(newToken);
				response.setHeader("token", newToken);
			}
		} else {
			responseBean.setFlag("failure");
			responseBean.setMessage("Parameters not found");
			throw new FailedToLoginException(credentials.getUsername());
		}
		return responseBean;
		
	}
}
