package com.rvm.gym.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Exercício de um treino")
public class ExercicioResponseDto {

    @Schema(description = "Nome do exercício", example = "Supino reto com barra")
    private String nome;

    @Schema(description = "Grupo muscular trabalhado", example = "Peito")
    private String grupoMuscular;
}
