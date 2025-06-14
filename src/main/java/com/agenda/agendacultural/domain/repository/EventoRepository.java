package com.agenda.agendacultural.domain.repository;

import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.model.enums.EventoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String>, EventoRepositoryCustom {

    List<Evento> findByUsuarioEmail(String email);
    List<Evento> findByStatus(EventoStatus status);

}
