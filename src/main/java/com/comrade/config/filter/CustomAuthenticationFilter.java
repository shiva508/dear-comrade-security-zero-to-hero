package com.comrade.config.filter;

import com.comrade.config.authentication.CustomAuthentication;
import com.comrade.config.manager.CustomAuthenticationManager;
import com.comrade.model.InvalidLoginResponse;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomAuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /*
        * 1. Create unauthorised authentication object
        * 2. Send unauthorised authentication to authentication manager
        * 3. Get back authorized authentication object from authentication manager
        * 4. If authentication object is properly authorized then send to next filter
        * */
        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication unAuthenticationObj = new CustomAuthentication(false,key);
        Authentication authentication = authenticationManager.authenticate(unAuthenticationObj);

        if (authentication.isAuthenticated()){
            /*THIS SHOULD BE INVOKED ONLY WHEN AUTHENTICATION SUCCESSFUL */
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }else {
            return;
        }
//        else {
//            InvalidLoginResponse loginResponse=new InvalidLoginResponse();
//            String jsonLoginResponse=new Gson().toJson(loginResponse);
//            response.setContentType("application/json");
//            response.setStatus(401);
//            response.getWriter().print(jsonLoginResponse);
//        }

    }
}
