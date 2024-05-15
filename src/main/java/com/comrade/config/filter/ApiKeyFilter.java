package com.comrade.config.filter;

import com.comrade.config.authentication.ApiKeyAuthentication;
import com.comrade.config.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CustomAuthenticationManager manager = new CustomAuthenticationManager(key);

        String requestKey = request.getHeader("oauth-api-key");

        if (requestKey == null || "null".equals(requestKey) || requestKey.isEmpty()) {
            filterChain.doFilter(request, response);
        }

        Authentication unAuthorizedAuthObject = new ApiKeyAuthentication(requestKey);

        try {
            Authentication authorizedAuthObject = manager.authenticate(unAuthorizedAuthObject);
            if (authorizedAuthObject.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authorizedAuthObject);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
