package com.agenda.agendacultural.domain.service;

import com.agenda.agendacultural.domain.exception.NotFoundException;
import com.agenda.agendacultural.domain.model.Calendario;
import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.model.Usuario;
import com.agenda.agendacultural.domain.model.enums.EventoStatus;
import com.agenda.agendacultural.domain.repository.EventoRepository;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import com.agenda.agendacultural.infraestructure.mapper.CalendarioMapper;
import com.agenda.agendacultural.infraestructure.mapper.EventoMapper;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    private final EventoMapper eventoMapper;

    private final UsuarioService usuarioService;

    private final CalendarioMapper calendarioMapper;

    @Autowired
    public EventoService(EventoRepository eventoRepository,
                         EventoMapper eventoMapper,
                         UsuarioService usuarioService,
                         CalendarioMapper calendarioMapper) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
        this.usuarioService = usuarioService;
        this.calendarioMapper = calendarioMapper;
    }


    public Evento salvar(EventoDTO dto) {
        Usuario usuario = usuarioService.buscarUsuarioLogado();

        Evento evento = eventoMapper.toEntity(dto);
        evento.setUsuario(usuario);
        evento.setStatus(EventoStatus.EM_ANALISE);
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
