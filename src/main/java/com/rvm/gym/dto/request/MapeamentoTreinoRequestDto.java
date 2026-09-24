package com.rvm.gym.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @NotBlank
    @Size(min = 1, max = 1)
    @EqualsAndHashCode.Include
    private String nome;

    @Min(1)
    @Max(7)
    private Integer ordem;

    @Valid
    @NotEmpty
    @UniqueElements
    private List<MapeamentoExercicioRequestDto> mapeamentosExercicios;
}
