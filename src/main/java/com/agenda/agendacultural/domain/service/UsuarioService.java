package com.agenda.agendacultural.domain.service;



import com.agenda.agendacultural.domain.exception.BusinessException;
import com.agenda.agendacultural.domain.model.Usuario;
import com.agenda.agendacultural.domain.exception.DuplicationException;
import com.agenda.agendacultural.domain.exception.NotFoundException;
import com.agenda.agendacultural.domain.repository.UsuarioRepository;
import com.agenda.agendacultural.domain.repository.specification.UsuarioSpecifications;
import com.agenda.agendacultural.infraestructure.dto.UsuarioDTO;
import com.agenda.agendacultural.infraestructure.dto.UsuarioSenhaDTO;
import com.agenda.agendacultural.infraestructure.mapper.UsuarioMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioMapper usuarioMapper,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvar(UsuarioDTO dto) {
        if (existeUsuarioComEmail(dto.getEmail())) {
            throw new DuplicationException("Já existe um usuário cadastrado com esse e-mail!");
        }
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        return usuarioRepository.save(usuario);


    }

    public void alterarSenha(UsuarioSenhaDTO dto) {
        Usuario usuario = buscarUsuarioLogado();

        if(!passwordEncoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            throw new BusinessException("A senha informada está incorreta.");
        }
        usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        usuarioRepository.save(usuario);
    }

   @Transactional
   public void deleteUsuario (String id) {
       Usuario usuario = buscarUsuario(id);
       usuarioRepository.delete(usuario);
   }

    public Usuario buscarUsuario(String id) {
        return usuarioRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Usuário não encontrado"));
    }

    public Page<Usuario> buscarUsuarios(String perfil, Pageable pageable) {
        Specification<Usuario> spec = UsuarioSpecifications.comPerfil(perfil);
        return usuarioRepository.findAll(spec, pageable);
    }

    public Page<Usuario> buscarUsuarios(String perfil, String email, Pageable pageable) {
        List<Specification<Usuario>> specifications = new ArrayList<>();

        if (perfil != null && !perfil.trim().isEmpty()) {
            specifications.add(UsuarioSpecifications.comPerfil(perfil));
        }
        if (email != null && !email.trim().isEmpty()) {
            specifications.add(UsuarioSpecifications.comEmail(email));
        }

        Specification<Usuario> finalSpecification = Specification.allOf(specifications);

        return usuarioRepository.findAll(finalSpecification, pageable);
    }

    private boolean existeUsuarioComEmail (String email) {
        return usuarioRepository
                .findByEmail(email)
                .isPresent();
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() ->
                 new NotFoundException("Usuário não encontrado"));
    }

    public Usuario buscarUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return buscarUsuarioPorEmail(email);
    }





}