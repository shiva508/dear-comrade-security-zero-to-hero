package com.comrade.config;


import com.comrade.config.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class DhaSecurityConfiguration {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Value("${reactjs.security.secret-key}")
    String key;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize->authorize
                                .anyRequest()
                                //.hasRole("ADMIN")
                                //.hasAuthority("read")
                                //.hasAnyRole("USER")
                                //.hasAnyAuthority("write")
                                //.permitAll()
                                //.denyAll()
                                .authenticated()
                        )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetailsOne = User.builder().username("shiva").password("shiva").authorities("read").build();
        UserDetails userDetailsTwo = User.builder().username("dasari").password("dasari").authorities("write").build();

        /*
        * role and authorities both use same contract GrantedAuthority
        * When working with roles.
        * roles --> ADMIN,USER
        * authorities --> ROLE_ADMIN,ROLE_USER
        * */
        inMemoryUserDetailsManager.createUser(userDetailsOne);
        inMemoryUserDetailsManager.createUser(userDetailsTwo);
        return inMemoryUserDetailsManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
