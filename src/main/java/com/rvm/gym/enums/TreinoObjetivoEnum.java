package com.rvm.gym.enums;

import com.rvm.gym.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum TreinoObjetivoEnum {

    AUMENTO_MASSA_MUSCULAR(UUID.fromString("48dd94fa-e605-4212-9287-750053c6341b"), "Aumento de Massa Muscular"),
    CONDICIONAMENTO_FISICO(UUID.fromString("a9eac43d-39d5-4d8e-a966-73b5128b4ba9"), "Condicionamento Físico");

    private final UUID id;
    private final String descricao;

    public static TreinoObjetivoEnum fromId(UUID id) {
        if (id == null) {
            throw new BusinessException("Objetivo para o treino inválido.");
        }

        return Arrays.stream(values())
                     .filter(obj -> obj.getId()
                                       .equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Objetivo de treino inválido para o ID: " + id));
    }
}