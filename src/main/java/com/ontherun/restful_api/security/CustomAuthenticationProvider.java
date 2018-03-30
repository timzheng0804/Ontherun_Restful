package com.ontherun.restful_api.security;

import com.ontherun.restful_api.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Custom Authentication for authenticating visiting users
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserServiceImpl userService;

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

    /**
     * Custom Authentication is not managed by Spring, need injection from outside for user Service
     * @param userService user Service
     */
    public CustomAuthenticationProvider(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phone = authentication.getName();
        String password = authentication.getCredentials().toString();
        logger.info("Phone :" + phone + " Password :" + password);
        // Authenticate User. User Service throws exception if phone and password not matching.
        Long userId = userService.authenticateUser(phone, password);
        return new UsernamePasswordAuthenticationToken(userId, null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
