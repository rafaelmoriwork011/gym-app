package com.rvm.gym.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Resultado da verificação de renovação do treino")
public class ValidacaoTreinoRenovacaoResponseDto {

    @Schema(description = "Indica se o treino deve ser renovado", example = "true")
    private boolean deveRenovar;
}
