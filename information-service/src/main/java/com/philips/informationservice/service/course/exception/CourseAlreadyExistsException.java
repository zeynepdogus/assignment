package com.philips.informationservice.service.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Course already exists.")
public class CourseAlreadyExistsException extends RuntimeException{
    public CourseAlreadyExistsException() {
    }

    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}
