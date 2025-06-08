package com.agenda.agendacultural.domain.model;

import com.agenda.agendacultural.domain.model.enums.EventoStatus;
import com.agenda.agendacultural.domain.model.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "local", nullable = false, length = 255)
    private String local;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "imagem-url", columnDefinition = "TEXT")
    private String imagemUrl;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Calendario> calendarios;

    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCategoria categoria;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventoStatus status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
