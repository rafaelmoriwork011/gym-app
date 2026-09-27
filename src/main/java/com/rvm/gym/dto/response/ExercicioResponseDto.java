package com.rvm.gym.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExercicioResponseDto {

    private String nome;
    private String grupoMuscular;
}
