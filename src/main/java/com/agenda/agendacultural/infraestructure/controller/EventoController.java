package com.agenda.agendacultural.infraestructure.controller;

import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.model.enums.EventoStatus;
import com.agenda.agendacultural.domain.model.enums.TipoCategoria;
import com.agenda.agendacultural.domain.service.EventoService;
import com.agenda.agendacultural.infraestructure.dto.EventoAlteracaoDTO;
import com.agenda.agendacultural.infraestructure.dto.EventoDTO;
import com.agenda.agendacultural.infraestructure.dto.EventoStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable String id, @RequestBody @Valid EventoAlteracaoDTO eventoDTO) {
        return new ResponseEntity<>(eventoService.alterarEvento(id, eventoDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Evento> alterarStatusEvento(@PathVariable String id, @RequestBody @Valid EventoStatusDTO status) {
        return new ResponseEntity<>(eventoService.alterarStatusEvento(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um evento por ID")
    public ResponseEntity<Void>deletarEvento(@PathVariable String id){
        eventoService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar todos os eventos")
    public ResponseEntity<List<Evento>> listarTodosEventos(@RequestParam(required = false)
                                                           @DateTimeFormat(pattern = "dd/MM/yyyy")
                                                           LocalDate inicio,
                                                           @RequestParam(required = false)
                                                           @DateTimeFormat(pattern = "dd/MM/yyyy")
                                                           LocalDate fim,
                                                           @RequestParam(required = false)
                                                           TipoCategoria categoria) {
        List<Evento> eventos = eventoService.buscarCategoriaData(inicio, fim, categoria);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/meus-eventos")
    @Operation(summary = "Listar todos os eventos criados pelo usuario")
    public ResponseEntity<List<Evento>> listarMeusEventos() {
        List<Evento> eventos = eventoService.buscarMeusEventos();
        return ResponseEntity.ok(eventos);
    }

}
