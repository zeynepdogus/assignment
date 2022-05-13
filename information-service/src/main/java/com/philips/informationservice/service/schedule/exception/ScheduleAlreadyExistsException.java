package com.philips.informationservice.service.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Schedule already exists.")
public class ScheduleAlreadyExistsException extends RuntimeException{
    public ScheduleAlreadyExistsException() {
    }

    public ScheduleAlreadyExistsException(String message) {
        super(message);
    }
}
