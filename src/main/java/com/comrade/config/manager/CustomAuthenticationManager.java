package com.comrade.config.manager;

import com.comrade.config.provider.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider(key);
        if (customAuthenticationProvider.supports(authentication.getClass())){
           return customAuthenticationProvider.authenticate(authentication);
        }
        throw new BadCredentialsException("Please provide valid credentials");
    }
}
