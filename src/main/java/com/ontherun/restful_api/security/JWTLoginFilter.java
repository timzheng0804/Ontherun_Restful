package com.ontherun.restful_api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontherun.restful_api.domain.UserCredentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Login filter using custom authentication provider to authenticate user.
 * Return JWT token if user successfully authenticated
 * Return Authentication Failed if unsuccess
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {


    public JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authenticationManager);
    }

    /**
     * Validating user login info.
     * Calls Custom Authentication class authenticate method to authenticate login info.
     * @param request Http request
     * @param response Http response
     * @return Authentication
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // Get login information from request
        UserCredentials userCredentials = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getPhone(),
                        userCredentials.getPassword()));
    }

    /**
     * Return JWT Token to user
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        // Authentication success, give user JWT Token for accessing api
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
    }

    /**
     * Unsuccessful Authentication, return Authentication Failed
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println("Authentication Failed");
    }
}