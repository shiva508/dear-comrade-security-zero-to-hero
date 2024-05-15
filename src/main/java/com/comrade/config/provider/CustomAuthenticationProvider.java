package com.comrade.config.provider;

import com.comrade.config.authentication.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication customAuthentication =  (ApiKeyAuthentication)authentication;
        String requestKey = customAuthentication.getKey();
        if (key.equals(requestKey)){
            customAuthentication.setAuthenticated(true);
            return customAuthentication;
        }
        throw new BadCredentialsException("Please provide valid credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
