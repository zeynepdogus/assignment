package com.philips.informationservice.service.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Department could not be created.")
public class DepartmentCreateException extends RuntimeException{
    public DepartmentCreateException() {
    }

    public DepartmentCreateException(String message) {
        super(message);
    }
}
