package com.rvm.gym.dto.request;

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
public class MapeamentoTreinoRequestDto {

    @Valid
    @NotEmpty
    @UniqueElements
    private List<MapeamentoExercicioRequestDto> mapeamentosExercicios;
}
