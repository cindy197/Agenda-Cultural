package com.agenda.agendacultural.infraestructure.mapper;

import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    Evento toEntity(EventoDTO dto);

    EventoDTO toDto(Evento entity);

}
