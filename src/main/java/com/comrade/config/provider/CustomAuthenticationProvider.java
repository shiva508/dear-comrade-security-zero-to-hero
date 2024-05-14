package com.comrade.config.provider;

import com.comrade.config.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${reactjs.security.secret-key}")
    String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication =  (CustomAuthentication)authentication;
        String requestKey = customAuthentication.getKey();
        if (key.equals(requestKey)){
           return new CustomAuthentication(true,null);
        }
        throw new BadCredentialsException("Please provide valid credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
