package com.agenda.agendacultural.domain.exception;


import com.agenda.agendacultural.infraestructure.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicationException extends BaseException {
    public DuplicationException(String message) {
        super(message);
    }
}