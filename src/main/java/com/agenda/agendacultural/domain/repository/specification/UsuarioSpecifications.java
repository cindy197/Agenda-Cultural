package com.agenda.agendacultural.domain.repository.specification;


import com.agenda.agendacultural.domain.Usuario;
import org.springframework.data.jpa.domain.Specification;

public final class UsuarioSpecifications {

    public static Specification<Usuario> comPerfil(String perfil) {
        return (root, query, criteriaBuilder) -> {
            if (perfil == null || perfil.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("perfil")), "%" + perfil.toLowerCase() + "%");
        };
    }

    public static Specification<Usuario> comEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("email")), email.toLowerCase());
        };
    }
}