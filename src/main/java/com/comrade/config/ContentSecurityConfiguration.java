package com.comrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ContentSecurityConfiguration {

    //Before lamda
    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


            http.authorizeHttpRequests()
                    .requestMatchers("/api/welcome/**").permitAll()
                    .requestMatchers("/api/content/**").hasAnyRole("SUPER_APPROVER")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .csrf()
                    .disable()
                    .httpBasic(Customizer.withDefaults());
        return http.build();
    }
     */
    @Bean
    public SecurityFilterChain securityFilterChainDsl(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(authreqz ->{
                    authreqz.requestMatchers("/api/welcome/**").permitAll();
                    authreqz.requestMatchers("/api/content/**").hasAnyRole("SUPER_APPROVER");
                    authreqz.anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
