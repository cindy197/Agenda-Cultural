package com.agenda.agendacultural.domain.repository;

import com.agenda.agendacultural.domain.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String> {

}
