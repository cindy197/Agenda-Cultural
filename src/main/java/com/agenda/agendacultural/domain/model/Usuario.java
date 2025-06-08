package com.agenda.agendacultural.domain.model;

import com.agenda.agendacultural.domain.model.enums.UsuarioPerfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Email
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @JsonIgnore
    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "perfil", nullable = false)
    @Enumerated(EnumType.STRING)
    private UsuarioPerfil perfil;



}