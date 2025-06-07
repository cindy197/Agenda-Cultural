package com.agenda.agendacultural.infraestructure.controller;

import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.service.EventoService;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventos")
@Tag(name = "Eventos", description = "API para gerenciamento de eventos")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody @Valid EventoDTO eventoDTO) {
        return new ResponseEntity<>(eventoService.salvar(eventoDTO), HttpStatus.CREATED);
    }
}
