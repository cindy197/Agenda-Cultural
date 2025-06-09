package com.agenda.agendacultural.domain.repository;

import com.agenda.agendacultural.domain.model.Evento;
import com.agenda.agendacultural.domain.model.enums.TipoCategoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jdk.jfr.Event;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Repository
public class EventoRepositoryCustomImpl implements EventoRepositoryCustom{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Evento> findComFiltros(LocalDate inicio, LocalDate fim, TipoCategoria categoria) {
        var jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT e FROM Evento e JOIN e.calendarios c WHERE 1=1 ");

        var parametros = new HashMap<String, Object>();

        if (inicio != null && fim != null) {
            jpql.append("AND c.data BETWEN :dataInicio AND :dataFim ");
            parametros.put("dataInicio", inicio);
            parametros.put("dataFim", fim);
        }

        if (categoria != null) {
            jpql.append("AND e.categoria = :categoria ");
            parametros.put("categoria", categoria);
        }

        TypedQuery<Evento> query = manager.createQuery(jpql.toString(), Evento.class);

        parametros.forEach(query::setParameter);

        return query.getResultList();

    }
}
