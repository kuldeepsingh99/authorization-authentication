package com.portal.auto.bean;



import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthToken implements Authentication {
    
	private static final long serialVersionUID = 1L;
	
	private final String token;

    public JwtAuthToken(String token) {
        this.token = token;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return null;
    }


    public Object getCredentials() {
        return token;
    }


    public Object getDetails() {
        return null;
    }


    public Object getPrincipal() {
        return null;
    }


    public boolean isAuthenticated() {
        return false;
    }


    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }


    public String getName() {
        return null;
    }
}
