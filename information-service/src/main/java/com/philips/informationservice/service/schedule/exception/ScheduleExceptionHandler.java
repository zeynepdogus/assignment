package com.philips.informationservice.service.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception class
 */
public abstract class ScheduleExceptionHandler extends RuntimeException {

    public ScheduleExceptionHandler() {
    }

    public ScheduleExceptionHandler(String message) {
        super(message);
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Schedule already exists.")
    public static class ScheduleAlreadyExistsException extends ScheduleExceptionHandler {


    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Schedule could not be created.")
    public static class ScheduleCreateException extends ScheduleExceptionHandler {

    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Schedule not found.")
    public static class ScheduleNotFoundException extends ScheduleExceptionHandler {

    }
}
