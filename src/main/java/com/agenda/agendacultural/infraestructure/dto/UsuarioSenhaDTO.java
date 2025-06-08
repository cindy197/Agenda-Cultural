package com.agenda.agendacultural.infraestructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioSenhaDTO {

    @Schema(description = "Senha Atual de acesso do usuário.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Senha Atual não pode ser vazia")
    @Size(min = 10, max = 50, message = "Senha Atual deve ter de 10 a 50 caracteres")
    @JsonProperty("senha-atual")
    private String senhaAtual;

    @Schema(description = "Nova senha de acesso do usuário.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Nova senha não pode ser vazia")
    @Size(min = 10, max = 50, message = "Nova senha deve ter de 10 a 50 caracteres")
    @JsonProperty("nova-senha")
    private String novaSenha;
}
