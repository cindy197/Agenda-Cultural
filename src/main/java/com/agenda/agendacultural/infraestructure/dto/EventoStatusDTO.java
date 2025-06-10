package com.agenda.agendacultural.infraestructure.dto;

import com.agenda.agendacultural.domain.model.enums.EventoStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoStatusDTO {

    @NotNull(message = "Status não pode ser vazio")
    private EventoStatus status;
}
