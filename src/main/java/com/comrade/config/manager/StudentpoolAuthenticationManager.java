package com.comrade.config.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.comrade.config.authentication.StudentpoolAuthentication;
import com.comrade.config.provider.StudentpoolAuthenticationProvider;

@Component
@RequiredArgsConstructor
public class StudentpoolAuthenticationManager implements AuthenticationManager {

	private final StudentpoolAuthenticationProvider studentpoolAuthenticationProvider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (studentpoolAuthenticationProvider.supports(StudentpoolAuthentication.class)) {
			return studentpoolAuthenticationProvider.authenticate(authentication);
		} else {
			throw new BadCredentialsException("Bad too bad");
		}
	}
}
