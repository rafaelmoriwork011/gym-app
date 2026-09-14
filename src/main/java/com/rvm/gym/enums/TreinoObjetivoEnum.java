package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum TreinoObjetivoEnum {

    AUMENTO_MASSA_MUSCULAR(UUID.fromString("f0000000-0000-4000-8000-000000000001"), "Aumento de Massa Muscular"),
    EMAGRECIMENTO(UUID.fromString("f0000000-0000-4000-8000-000000000002"), "Emagrecimento"),
    CONDICIONAMENTO_FISICO(UUID.fromString("f0000000-0000-4000-8000-000000000003"), "Condicionamento Físico");

    private final UUID id;
    private final String descricao;

    public static TreinoObjetivoEnum fromId(UUID id) {
        if (id == null) return null;

        return Arrays.stream(values())
                     .filter(obj -> obj.getId().equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Objetivo de treino inválido para o ID: " + id));
    }
}