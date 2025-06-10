package com.agenda.agendacultural.infraestructure.dto;

import com.agenda.agendacultural.domain.model.enums.TipoCategoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoAlteracaoDTO {

    @NotBlank(message = "Descrição não pode ser vazia")
    @JsonProperty("descricao")
    private String descricao;

    @NotBlank(message = "Local não pode ser vazio")
    @JsonProperty("local")
    private String local;

    @JsonProperty("preco")
    private Double preco;

    @JsonProperty("imagem-url")
    private String imagemUrl;

    @JsonProperty("calendarios")
    private List<CalendarioDTO> calendarios;

    @JsonProperty("categoria")
    @NotNull(message = "Categoria não pode ser vazio")
    private TipoCategoria categoria;
}
