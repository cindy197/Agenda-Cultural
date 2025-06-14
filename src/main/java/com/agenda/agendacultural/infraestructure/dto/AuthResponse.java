package com.agenda.agendacultural.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String nome;
    private String perfil;
}
