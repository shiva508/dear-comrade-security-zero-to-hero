package com.comrade.config.filter;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.comrade.config.authentication.StudentpoolAuthentication;
import com.comrade.config.manager.StudentpoolAuthenticationManager;

@Component
@RequiredArgsConstructor
public class StudentpoolAuthenticationFilter extends OncePerRequestFilter {

	private final StudentpoolAuthenticationManager authenticationManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//1.Create an Authentication object which is not authenticated
		String key = request.getHeader("key");
		StudentpoolAuthentication studentpoolAuthentication = new StudentpoolAuthentication(false, key);
		//2.Delegate Authentication object to Manager 
		//3.Getback the authenticated object from manager
		
		var authenticated = authenticationManager.authenticate(studentpoolAuthentication);
		//4.If objecte is aubtneticated send request to filter chian
		if (authenticated.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticated);
			filterChain.doFilter(request, response);
		}else {
			throw new BadCredentialsException("Credential issue");
		}

	}

}
