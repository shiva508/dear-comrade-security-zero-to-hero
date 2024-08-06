package com.comrade.config.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.comrade.config.authentication.StudentpoolAuthentication;

@Component
public class StudentpoolAuthenticationProvider implements AuthenticationProvider {
	
	@Value("${security.secretekey}")
	private String secretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		StudentpoolAuthentication studentpoolAuthentication = (StudentpoolAuthentication) authentication;
		String key = studentpoolAuthentication.getKey();
		if(key.equals(secretKey)) {
			return new StudentpoolAuthentication(true,null);
		}else {
			throw new BadCredentialsException("Issue with credentials Failed");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return StudentpoolAuthentication.class.equals(authentication);
	}

}
