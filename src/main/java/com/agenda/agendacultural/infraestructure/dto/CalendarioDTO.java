package com.agenda.agendacultural.infraestructure.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioDTO {

    @NotNull
    @JsonProperty("data")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    public LocalDate data;

    @NotNull
    @JsonProperty("hora-inicio")
    public int horaInicio;

    @NotNull
    @JsonProperty("hora-fim")
    public int horaFim;
}
