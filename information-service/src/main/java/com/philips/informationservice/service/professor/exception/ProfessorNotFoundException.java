package com.philips.informationservice.service.professor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Professor not found.")
public class ProfessorNotFoundException extends RuntimeException {

    public ProfessorNotFoundException() {
    }

    public ProfessorNotFoundException(String message) {
        super(message);
    }
}
