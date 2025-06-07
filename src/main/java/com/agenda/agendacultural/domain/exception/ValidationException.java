package com.agenda.agendacultural.domain.exception;


import com.agenda.agendacultural.infraestructure.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends BaseException {
    public ValidationException(String message) {
        super(message);
    }
}