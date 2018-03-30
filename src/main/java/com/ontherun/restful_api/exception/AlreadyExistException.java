package com.ontherun.restful_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistException extends RuntimeException {

    /**
     * Already exist exception. eg. Username already exist.
     * @param message
     */
    public AlreadyExistException(String message) {
        super(message + " Already Existed.");
    }

}
