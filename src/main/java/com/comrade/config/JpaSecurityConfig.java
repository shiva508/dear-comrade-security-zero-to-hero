package com.comrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.comrade.config.handler.CustomAccessDeniedHandler;
import com.comrade.config.handler.CustomAuthenticationFailureHandler;
import com.comrade.service.UserProfileService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JpaSecurityConfig {
	
	private final UserProfileService userProfileService;
	
	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	private final CustomAccessDeniedHandler deniedHandler;
	
	
	public JpaSecurityConfig(UserProfileService userProfileService, 
							 CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
							 CustomAccessDeniedHandler deniedHandler) {
		this.userProfileService = userProfileService;
		this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
		this.deniedHandler = deniedHandler;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth ->
						//auth.requestMatchers("/api/posts/**")
												   //.permitAll()
						auth.anyRequest()
													.authenticated())
				.userDetailsService(userProfileService)
				.httpBasic(Customizer.withDefaults())
				.exceptionHandling(hsehc -> hsehc.accessDeniedHandler(deniedHandler))
				.formLogin(form ->form.failureHandler(customAuthenticationFailureHandler))
				.build();
		/*
		return http
				.csrf(csrf->csrf.disable())
				.authorizeRequests(auth->auth
				.antMatchers("/api/posts/**").permitAll()
				.anyRequest()
				.authenticated())
				.formLogin()
				//.failureHandler(failureHandler)
				.and()
				.userDetailsService(userProfileService)
				.httpBasic(Customizer.withDefaults())
				.exceptionHandling()
				.accessDeniedHandler(deniedHandler)
				.and()
				.build();

		 */
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
