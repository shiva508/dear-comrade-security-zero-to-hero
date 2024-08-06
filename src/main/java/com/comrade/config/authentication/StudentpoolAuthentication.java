package com.comrade.config.authentication;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class StudentpoolAuthentication implements Authentication {
	
	private static final long serialVersionUID = 1L;
	private boolean isAuthenticated;
	private String key;
	
	public StudentpoolAuthentication(boolean isAuthenticated, String key) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated=isAuthenticated;
	}

}
