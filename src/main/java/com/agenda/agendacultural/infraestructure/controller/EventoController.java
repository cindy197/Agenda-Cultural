package com.agenda.agendacultural.infraestructure.controller;

import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.service.EventoService;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um evento por ID")
    public ResponseEntity<Evento> buscarEventoId(@PathVariable String id) {
        Evento evento = eventoService.buscarPorId(id);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um evento por ID")
    public ResponseEntity<Void>deletarEvento(@PathVariable String id){
        eventoService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar todos os eventos")
    public ResponseEntity<List<Evento>> listarTodosEventos() { //Não vai ter o id, pois não vai filtrar por nada especifico
        List<Evento> eventos = eventoService.buscarTodos();
        return ResponseEntity.ok(eventos);
    }
}
