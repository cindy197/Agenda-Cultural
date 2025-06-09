package com.agenda.agendacultural.domain.repository;

import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.model.enums.TipoCategoria;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepositoryCustom {

    List<Evento> findComFiltros(LocalDate inicio, LocalDate fim, TipoCategoria categoria);
}
