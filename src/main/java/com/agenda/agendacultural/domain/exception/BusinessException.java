package com.agenda.agendacultural.domain.exception;


import com.agenda.agendacultural.infraestructure.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message);
    }
}