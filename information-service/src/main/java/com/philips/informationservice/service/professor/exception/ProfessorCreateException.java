package com.philips.informationservice.service.professor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Professor could not be created.")
public class ProfessorCreateException extends RuntimeException{
    public ProfessorCreateException() {
    }

    public ProfessorCreateException(String message) {
        super(message);
    }
}
