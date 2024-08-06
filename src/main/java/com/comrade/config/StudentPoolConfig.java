package com.comrade.config;

import com.comrade.config.filter.CsrfLoggerTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;


@Configuration
public class StudentPoolConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// http.csrf().disable();
		http.authorizeHttpRequests(amrmr ->amrmr.anyRequest().authenticated())
				.formLogin(hsflc ->hsflc.loginPage("/login").permitAll()) //can either be mapping or file
				.logout(hslc -> hslc.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID").clearAuthentication(true).permitAll()) //logout configuration
				.addFilterAfter(new CsrfLoggerTokenFilter(), CsrfFilter.class)
				.csrf(hscc -> hscc.ignoringRequestMatchers("/csrfconfigdisable/**").csrfTokenRepository(new StudentpoolCsrfTokenRepository()));
		/*
		http.authorizeHttpRequests().anyRequest().authenticated().and().formLogin().loginPage("/login") // can either be
																									// mapping or file
				.permitAll().and()
				// logout configuration
				.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").clearAuthentication(true).permitAll().and().addFilterAfter(new CsrfLoggerTokenFilter(), CsrfFilter.class);
		
		http.csrf((c) -> {
			c.ignoringRequestMatchers("/csrfconfigdisable/**");
			c.csrfTokenRepository(new StudentpoolCsrfTokenRepository());
		});

		 */
		return http.build();
	}
}
