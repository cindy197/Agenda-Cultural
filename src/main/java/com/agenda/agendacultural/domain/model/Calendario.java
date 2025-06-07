package com.agenda.agendacultural.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "calendario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "hora_inicio", nullable = false)
    private int horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private int horaFim;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "evento_id")
    private Evento evento;
}
