package com.portal.auto.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portal.auto.bean.LoginCredentials;
import com.portal.auto.bean.ResponseBean;
import com.portal.auto.bean.UserBean;
import com.portal.auto.exception.FailedToLoginException;
import com.portal.auto.security.JwtService;
import com.portal.auto.service.LoginService;

@RestController
public class LoginController {

	@Autowired
    private JwtService jwtService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(path = "/login" , method=RequestMethod.POST, consumes = "application/json" ,produces ="application/json")
    public ResponseBean login(@RequestBody LoginCredentials credentials,
                                HttpServletResponse response) {

    			
    	ResponseBean responseBean = new ResponseBean();
    	UserBean user = loginService.findByUserName(credentials.getUsername());
    	
    	if(null == user){
    		throw new FailedToLoginException(credentials.getUsername());
    	}
    	if(null != user && !passwordEncoder.matches(credentials.getPassword(), user.getPassword())){
    		responseBean.setFlag("failure");
    		responseBean.setMessage("Exception occured");
    		throw new FailedToLoginException(credentials.getUsername());
    	}
    	
    	String token = null;
        try {
        	token = jwtService.tokenFor(credentials.getUsername(),user.getRole());
            response.setHeader("Token", token);
            loginService.updateToken(user, token, credentials.getUserType());
        } catch (Exception e) {
        	responseBean.setFlag("failure");
    		responseBean.setMessage("Exception occured");
            throw new RuntimeException(e);
        }
        responseBean.setRole(user.getRole());
        responseBean.setFlag("success");
		responseBean.setMessage(token);
		
		return responseBean;
	}
}
