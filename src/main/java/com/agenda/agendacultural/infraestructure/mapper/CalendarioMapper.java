package com.agenda.agendacultural.infraestructure.mapper;

import com.agenda.agendacultural.domain.model.Calendario;
import com.agenda.agendacultural.infraestructure.dto.CalendarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalendarioMapper {
    Calendario toEntity(CalendarioDTO dto);

    CalendarioDTO toDto(Calendario entity);
}
