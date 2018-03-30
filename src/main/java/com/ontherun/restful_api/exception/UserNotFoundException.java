package com.ontherun.restful_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Can not find user in database exception
     * @param userId
     */
    public UserNotFoundException(Long userId) {
        super("Could not find user '" + userId + "'.");
    }
}
