package com.agenda.agendacultural.domain.service;

import com.agenda.agendacultural.domain.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarioService {


    @Autowired private final CalendarioRepository calendarioRepository;

    public CalendarioService(CalendarioRepository calendarioRepository) {
        this.calendarioRepository = calendarioRepository;
    }
}
