package com.philips.informationservice.service.professor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Professor already exists.")
public class ProfessorAlreadyExistsException extends RuntimeException{
    public ProfessorAlreadyExistsException() {
    }

    public ProfessorAlreadyExistsException(String message) {
        super(message);
    }
}
