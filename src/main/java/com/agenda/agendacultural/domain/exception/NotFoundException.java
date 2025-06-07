package com.agenda.agendacultural.domain.exception;


import com.agenda.agendacultural.infraestructure.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }
}