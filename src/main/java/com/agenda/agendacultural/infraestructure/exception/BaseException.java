package com.agenda.agendacultural.infraestructure.exception;

public class BaseException extends RuntimeException{

    public BaseException(String mensagem) {
        super(mensagem);
    }

}