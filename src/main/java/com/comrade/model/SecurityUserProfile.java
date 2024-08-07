package com.comrade.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import io.micrometer.common.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.comrade.entity.UserProfile;

public class SecurityUserProfile implements UserDetails {
	
	private static final long serialVersionUID = 4258956719619819429L;
	
	private UserProfile userProfile;

	public SecurityUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		if(StringUtils.isBlank(userProfile.getRoles())) {
			userProfile.setRoles("ROLE_USER");
		}
		
		return Arrays.asList(userProfile.getRoles().split(","))
				.stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return userProfile.getPassword();
	}

	@Override
	public String getUsername() {
		return userProfile.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
