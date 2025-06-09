package com.agenda.agendacultural.domain.service;

import com.agenda.agendacultural.domain.exception.ForbiddenException;
import com.agenda.agendacultural.domain.exception.NotFoundException;
import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.model.Usuario;
import com.agenda.agendacultural.domain.model.enums.EventoStatus;
import com.agenda.agendacultural.domain.model.enums.TipoCategoria;
import com.agenda.agendacultural.domain.model.enums.UsuarioPerfil;
import com.agenda.agendacultural.domain.repository.EventoRepository;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import com.agenda.agendacultural.infraestructure.mapper.CalendarioMapper;
import com.agenda.agendacultural.infraestructure.mapper.EventoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Usuario usuario = usuarioService.buscarUsuarioLogado();
        Evento evento = buscarPorId(id);
        if (usuario.getPerfil() != UsuarioPerfil.ADMIN && !evento.getUsuario().getEmail().equals(usuario.getEmail())) {
            throw new ForbiddenException("Usuario não tem autorização para deletar evento! ");
        }

        eventoRepository.delete(evento);

    }

    public List<Evento> buscarTodos() {
        return eventoRepository.findAll();
    }

    public List<Evento> buscarCategoriaData(LocalDate inicio, LocalDate fim, TipoCategoria categoria) {
        return eventoRepository.findComFiltros(inicio, fim, categoria);
    }

    public List<Evento> buscarMeusEventos(){
        Usuario usuario = usuarioService.buscarUsuarioLogado();

        return eventoRepository.findByUsuarioEmail(usuario.getEmail());
    }



}
