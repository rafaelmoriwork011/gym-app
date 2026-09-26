package com.rvm.gym.dto.internal;

import com.rvm.gym.enums.GrupoMuscularEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MapeamentoExercicioDto {

    private GrupoMuscularEnum grupoMuscular;
    private Integer quantidadeExercicios;

    public void incrementarQuantidadeExercicios() {

        if (this.quantidadeExercicios == null) {
            this.quantidadeExercicios = 1;
            return;
        }

        this.quantidadeExercicios++;
    }

}
