package com.agenda.agendacultural.infraestructure.mapper;

import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CalendarioMapper.class)
public interface EventoMapper {

    Evento toEntity(EventoDTO dto);

    EventoDTO toDto(Evento entity);

    @AfterMapping
    default void setEventoInCalendarios(@MappingTarget Evento evento) {
        if (evento.getCalendarios() != null) {
            evento.getCalendarios().forEach(c ->
                    c.setEvento(evento));
        }
    }

}
