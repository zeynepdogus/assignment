package com.philips.informationservice.service.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception class
 */
public abstract class DepartmentExceptionHandler extends RuntimeException {

    public DepartmentExceptionHandler() {
    }

    public DepartmentExceptionHandler(String message) {
        super(message);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Department already exists.")
    public static class DepartmentAlreadyExistsException extends DepartmentExceptionHandler {


    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Department could not be created.")
    public static class DepartmentCreateException extends DepartmentExceptionHandler {

    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Department not found.")
    public static class DepartmentNotFoundException extends DepartmentExceptionHandler {

    }
}
