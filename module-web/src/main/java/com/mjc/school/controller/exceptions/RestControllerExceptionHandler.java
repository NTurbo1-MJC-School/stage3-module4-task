package com.mjc.school.controller.exceptions;

import com.mjc.school.service.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        NotFoundExceptionPayload notFoundExceptionPayload = new NotFoundExceptionPayload(
                e.getMessage(),
                e,
                notFoundStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity(notFoundExceptionPayload, notFoundStatus);
    }
}
