package com.agenda.agendacultural.infraestructure.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String codigo;
    private String mensagem;

    public ErrorResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public ErrorResponse(String mensagem, String codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

}