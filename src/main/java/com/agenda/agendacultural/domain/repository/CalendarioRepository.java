package com.agenda.agendacultural.domain.repository;

import com.agenda.agendacultural.domain.model.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, String> {
}
