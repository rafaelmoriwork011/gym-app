package com.rvm.gym.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(description = "Quantidade de exercícios para um grupo muscular")
public class MapeamentoExercicioRequestDto {

    @Schema(description = "ID do grupo muscular", example = "9b2d1f0e-4c1a-4f6b-8a55-2f3e6c1d7a90")
    @NotNull
    @EqualsAndHashCode.Include
    private UUID grupoMuscularId;

    @Schema(description = "Quantidade de exercícios para o grupo muscular", example = "3", minimum = "1")
    @Min(1)
    private Integer quantidadeExercicios;
}
