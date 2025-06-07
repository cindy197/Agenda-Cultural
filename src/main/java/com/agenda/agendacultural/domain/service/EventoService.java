package com.agenda.agendacultural.domain.service;

import com.agenda.agendacultural.domain.model.Calendario;
import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.model.enums.EventoStatus;
import com.agenda.agendacultural.domain.repository.EventoRepository;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import com.agenda.agendacultural.infraestructure.mapper.CalendarioMapper;
import com.agenda.agendacultural.infraestructure.mapper.EventoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    private final EventoMapper eventoMapper;

    private final CalendarioMapper calendarioMapper;

    @Autowired
    public EventoService(EventoRepository eventoRepository,
                         EventoMapper eventoMapper, CalendarioMapper calendarioMapper) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
        this.calendarioMapper = calendarioMapper;
    }


    public Evento salvar(EventoDTO dto) {
        Evento evento = eventoMapper.toEntity(dto);
        evento.setStatus(EventoStatus.EM_ANALISE);

        evento.setCalendarios(new ArrayList<>());

        dto.getCalendarios().forEach(c -> {
            Calendario calendario = calendarioMapper.toEntity(c);
            calendario.setEvento(evento);
            evento.getCalendarios().add(calendario);
        });

        return eventoRepository.save(evento);
    }

}
