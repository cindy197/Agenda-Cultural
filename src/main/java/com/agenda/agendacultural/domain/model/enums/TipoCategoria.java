package com.agenda.agendacultural.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum TipoCategoria {
    TEATRO("Teatro"),
    ESPORTES("Esportes"),
    TURISMO("Turismo"),
    CURSO("Curso"),
    GASTRONOMIA("Gastronomia"),
    CINEMA("Cinema"),
    HISTORIA_E_CULTURA("Historia e Cultura");

    private final String descricao;



}
