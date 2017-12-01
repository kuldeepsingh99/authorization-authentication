package com.portal.auto.exception;



import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    

    @SuppressWarnings("unchecked")
	@ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(FailedToLoginException.class)
    public @ResponseBody String handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
		
    	JSONObject obj = new JSONObject();
    	obj.put("url", request.getRequestURL().toString());
    	obj.put("message", ex.getMessage());
    	obj.put("status", UNAUTHORIZED);
		
		
		return obj.toJSONString();
	}

    @SuppressWarnings("unchecked")
	@ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(InvalidTokenException.class)
    public @ResponseBody String handleEmployeeNotFoundException1(HttpServletRequest request, Exception ex){
		
    	JSONObject obj = new JSONObject();
    	obj.put("url", request.getRequestURL().toString());
    	obj.put("message", ex.getMessage());
    	obj.put("status", UNAUTHORIZED);
		
		
		return obj.toJSONString();
	}
    
}
