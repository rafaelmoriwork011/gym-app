package com.rvm.gym.dto.internal;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MapeamentoTreinoDto {

    private String nome;
    private Integer ordem;
    private List<MapeamentoExercicioDto> mapeamentosExercicios;
}
