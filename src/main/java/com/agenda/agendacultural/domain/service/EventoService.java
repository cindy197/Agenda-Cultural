package com.agenda.agendacultural.domain.service;

import com.agenda.agendacultural.domain.exception.NotFoundException;
import com.agenda.agendacultural.domain.model.Calendario;
import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.model.enums.EventoStatus;
import com.agenda.agendacultural.domain.repository.EventoRepository;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import com.agenda.agendacultural.infraestructure.mapper.CalendarioMapper;
import com.agenda.agendacultural.infraestructure.mapper.EventoMapper;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

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


    public Evento buscarPorId(String id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento não encontrado!"));
    }

    public void deletarEvento(String id) {
        Evento evento = buscarPorId(id);
        eventoRepository.delete(evento);
    }

    public List<Evento> buscarTodos() {
        return eventoRepository.findAll();
    }

}
