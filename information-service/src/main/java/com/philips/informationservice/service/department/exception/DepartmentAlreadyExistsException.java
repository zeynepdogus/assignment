package com.philips.informationservice.service.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Department already exists.")
public class DepartmentAlreadyExistsException extends RuntimeException{
    public DepartmentAlreadyExistsException() {
    }

    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }
}
