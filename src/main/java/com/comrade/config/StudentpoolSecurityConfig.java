package com.comrade.config;

import com.comrade.config.handler.StudentpoolAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.comrade.config.filter.StudentpoolAuthenticationFilter;

@Configuration
public class StudentpoolSecurityConfig {
	private final StudentpoolAuthenticationFilter authenticationFilter;
	private final StudentpoolAuthenticationFailureHandler studentpoolAuthenticationFailureHandler;
	public StudentpoolSecurityConfig(StudentpoolAuthenticationFilter authenticationFilter,
									 StudentpoolAuthenticationFailureHandler studentpoolAuthenticationFailureHandler) {
		this.authenticationFilter = authenticationFilter;
		this.studentpoolAuthenticationFailureHandler = studentpoolAuthenticationFailureHandler;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				 //.and().exceptionHandling().accessDeniedHandler(authenticationFailureHadler)
				.and().build();
	}
}
