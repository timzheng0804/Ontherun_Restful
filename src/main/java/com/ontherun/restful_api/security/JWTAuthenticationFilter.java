package com.ontherun.restful_api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    /**
     * Filter needed to check if user have valid JWT Token
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // Check if JWT token is valid, null if invalid
        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest) servletRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
