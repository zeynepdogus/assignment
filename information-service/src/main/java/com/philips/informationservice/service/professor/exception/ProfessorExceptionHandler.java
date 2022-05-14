package com.philips.informationservice.service.professor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class ProfessorExceptionHandler extends RuntimeException {

    public ProfessorExceptionHandler() {
    }

    public ProfessorExceptionHandler(String message) {
        super(message);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Professor already exists.")
    public static class ProfessorAlreadyExistsException extends ProfessorExceptionHandler {


    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Professor could not be created.")
    public static class ProfessorCreateException extends ProfessorExceptionHandler {

    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Professor not found.")
    public static class ProfessorNotFoundException extends ProfessorExceptionHandler {

    }
}
