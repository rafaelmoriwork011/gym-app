package com.rvm.gym.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Treino com seus exercícios")
public class TreinoResponseDto {

    @Schema(description = "ID do treino", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Schema(description = "Nome do treino", example = "Treino A")
    private String nome;

    @Schema(description = "Exercícios do treino")
    @Builder.Default
    private List<ExercicioResponseDto> exercicios = new ArrayList<>();
}
