package com.agenda.agendacultural.infraestructure.dto;

import com.agenda.agendacultural.domain.model.UsuarioPerfil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    @Schema(description = "Nome completo do usuário.", example = "João da Silva Sauro", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 10, max = 80, message = "Nome deve ter de 10 a 80 caracteres")
    private String nome;

    @Schema(description = "Endereço de e-mail válido do usuário.", example = "joao.sauro@exemplo.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "Email deve ser válido")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    @Schema(description = "Senha de acesso do usuário.", example = "senhaForte@123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 10, max = 50, message = "Senha deve ter de 10 a 50 caracteres")
    private String senha;

    @Schema(description = "Perfil de acesso do usuário.", example = "VISITANTE", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Perfil não pode ser vazio")
    private UsuarioPerfil perfil;
}
