package com.philips.informationservice.service.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception class
 */
public abstract class CourseExceptionHandler extends RuntimeException {

    public CourseExceptionHandler() {
    }

    public CourseExceptionHandler(String message) {
        super(message);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Course already exists.")
    public static class CourseAlreadyExistsException extends CourseExceptionHandler {


    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Course could not be created.")
    public static class CourseCreateException extends CourseExceptionHandler {

    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Course not found.")
    public static class CourseNotFoundException extends CourseExceptionHandler {

    }
}
