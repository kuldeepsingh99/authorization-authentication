package com.portal.auto.bean;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@SuppressWarnings("serial")
public class JwtAuthenticatedProfile implements Authentication {

	private final String minimalProfile;

	private String userRole;
	
    public JwtAuthenticatedProfile(String minimalProfile) {
        this.minimalProfile = minimalProfile;
    }
    
    public void setUserRole(String userRole) {
		this.userRole = userRole;
	}



	public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.userRole));
    	
    }

   
    public Object getCredentials() {
        return null;
    }

    
    public Object getDetails() {
        return null;
    }

    
    public Object getPrincipal() {
        return null;
    }

    
    public boolean isAuthenticated() {
        return true;
    }

    
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    
    public String getName() {
        return minimalProfile;
    }

}
