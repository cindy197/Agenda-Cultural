package com.agenda.agendacultural.infraestructure.controller;


import com.agenda.agendacultural.domain.model.Usuario;
import com.agenda.agendacultural.domain.service.UsuarioService;
import com.agenda.agendacultural.infraestructure.dto.UsuarioDTO;
import com.agenda.agendacultural.infraestructure.dto.UsuarioSenhaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário no sistema e retorna os dados do usuário salvo, incluindo o ID gerado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)) }),

            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos ou violação de regra de negócio",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),

            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.salvar(usuarioDTO), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um usuário por ID", description = "Retorna os dados de um único usuário com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)) }),

            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),

            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(
            @Parameter(description = "ID do usuário a ser buscado", required = true, example = "8caee2b7-518f-453f-a24d-fb2a0d952320")
            @PathVariable String id) {
        return ResponseEntity.ok().body(usuarioService.buscarUsuario(id));
    }

    @PutMapping
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid UsuarioSenhaDTO usuarioDTO) {
        usuarioService.alterarSenha(usuarioDTO);
        return ResponseEntity.noContent().build();
    }

//    @ResponseStatus(HttpStatus.OK)
//    @Operation(summary = "Lista todos os usuários de forma paginada",
//            description = "Retorna uma lista paginada de usuários. É possível filtrar os resultados por perfil.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Page.class)) }),
//
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ErrorResponse.class)))
//    })
//    @GetMapping
//    public ResponseEntity<Page<Usuario>> listarUsuarios(
//            @Parameter(description = "Filtrar por nome do perfil do usuário", example = "ROLE_ADMIN")
//            @RequestParam(value = "perfil", required = false) String perfil,
//            Pageable pageable) {
//
//        return ResponseEntity.ok(usuarioService.buscarUsuarios(perfil, pageable));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") String id) {
//        usuarioService.deleteUsuario(id);
//        return ResponseEntity.noContent().build();
//
//    }



}