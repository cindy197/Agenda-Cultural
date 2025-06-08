package com.agenda.agendacultural.infraestructure.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String senha;
}
