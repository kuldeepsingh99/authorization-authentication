package com.portal.auto.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portal.auto.bean.RegisterBean;
import com.portal.auto.bean.ResponseBean;
import com.portal.auto.service.RegisterService;

@RestController
public class RegisterController {

	@Autowired
	public RegisterService registerService;
	
	@RequestMapping(path = "/register" , method=RequestMethod.POST, consumes = "application/json" ,produces ="application/json")
    public ResponseBean register(@RequestBody RegisterBean registerBean,
                                HttpServletResponse response) {
		return registerService.registerUser(registerBean);
		
	}
}
