package com.rvm.gym.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreinoResponseDto {

    private UUID id;
    private String nome;

    @Builder.Default
    private List<ExercicioResponseDto> exercicios = new ArrayList<>();

    public void addExercicio(ExercicioResponseDto exercicio) {
        this.exercicios.add(exercicio);
    }
}
