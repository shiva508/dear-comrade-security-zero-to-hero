package com.comrade.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class StudentpoolSecurityConfig {

	@Value("#{'${studentpool.cors.config.urls}'.split(',')}")
	private List<String> corsUrls;

	@Value("#{'${studentpool.cors.config.methods}'.split(',')}")
	private List<String> corsMethods;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				.cors(Customizer.withDefaults())
				.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
				.formLogin((form) -> form.successHandler((req, res, auth) -> res.setStatus(HttpStatus.OK.value()))
						.failureHandler((req, res, auth) -> res.setStatus(HttpStatus.UNAUTHORIZED.value())))
				.logout(logout -> logout
						.logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpStatus.OK.value())))
				.exceptionHandling(
						ex -> ex.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

		/*
		 * http.authorizeRequests().anyRequest().permitAll().and().formLogin().loginPage
		 * ("/login").permitAll().and()
		 * .logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(
		 * true)
		 * .deleteCookies("JSESSIONID").clearAuthentication(true).permitAll();
		 */
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

		UserDetails user1 = userBuilder.username("dasarishiva1@gmail.com").password("shiva").roles("admin").build();
		UserDetails user2 = userBuilder.username("dasarishiva.d2@gmail.com").password("shiva").roles("admin").build();

		return new InMemoryUserDetailsManager(user1, user2);

	}

	/*
	 * @Bean
	 * public CorsConfigurationSource corsConfigurationSource() {
	 * System.out.println(corsMethods);
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource();
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(corsUrls);
	 * configuration.setAllowedMethods(corsMethods);
	 * configuration.setAllowCredentials(true);
	 * source.registerCorsConfiguration("/**", configuration);
	 * return source;
	 * }
	 */

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.addAllowedOrigin("http://localhost:4200");
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}
