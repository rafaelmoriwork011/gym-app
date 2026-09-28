package com.rvm.gym.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Dados para configuração do treino de um usuário")
public class ConfiguracaoTreinoRequestDto {

    @Schema(description = "ID do usuário", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    @NotNull
    private UUID usuario;

    @Schema(description = "Observação livre sobre a configuração", example = "Foco em hipertrofia, sem treinar aos domingos")
    @Size(max = 255)
    private String observacao;

    @Schema(description = "ID do objetivo do treino", example = "7c9e6679-7425-40de-944b-e07fc1f90ae7")
    @NotNull
    private UUID objetivo;

    @Schema(description = "Treinos da semana, cada um com seus grupos musculares e quantidade de exercícios")
    @NotEmpty
    @Valid
    private List<MapeamentoTreinoRequestDto> mapeamentosTreinos;
}
