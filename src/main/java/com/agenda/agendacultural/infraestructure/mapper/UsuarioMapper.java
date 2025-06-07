package com.agenda.agendacultural.infraestructure.mapper;


import com.agenda.agendacultural.domain.Usuario;
import com.agenda.agendacultural.infraestructure.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDto(Usuario entity);
}