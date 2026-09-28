package com.rvm.gym.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(description = "Definição de um treino: lista de grupos musculares e quantidade de exercícios de cada um")
public class MapeamentoTreinoRequestDto {

    @Schema(description = "Grupos musculares do treino. Não pode haver grupos repetidos")
    @Valid
    @NotEmpty
    @UniqueElements
    private List<MapeamentoExercicioRequestDto> mapeamentosExercicios;
}
