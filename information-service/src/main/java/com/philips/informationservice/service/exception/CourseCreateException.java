package com.philips.informationservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Course could not be created.")
public class CourseCreateException extends RuntimeException{
    public CourseCreateException() {
    }

    public CourseCreateException(String message) {
        super(message);
    }
}
