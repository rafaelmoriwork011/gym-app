package com.rvm.gym.dto.request;

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
public class MapeamentoExercicioRequestDto {

    @NotNull
    @EqualsAndHashCode.Include
    private UUID grupoMuscularId;

    @Min(1)
    private Integer quantidadeExercicios;
}
