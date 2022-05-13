package com.philips.informationservice.service.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Schedule could not be created.")
public class ScheduleCreateException extends RuntimeException{
    public ScheduleCreateException() {
    }

    public ScheduleCreateException(String message) {
        super(message);
    }
}
