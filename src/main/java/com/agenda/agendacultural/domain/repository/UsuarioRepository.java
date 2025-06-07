package com.agenda.agendacultural.domain.repository;


import com.agenda.agendacultural.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>, JpaSpecificationExecutor<Usuario> {

}