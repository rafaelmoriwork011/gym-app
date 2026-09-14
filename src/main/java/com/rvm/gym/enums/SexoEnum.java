package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum SexoEnum {

    MASCULINO(UUID.fromString("a0000000-0000-4000-8000-000000000001"), "Masculino"),
    FEMININO(UUID.fromString("a0000000-0000-4000-8000-000000000002"), "Feminino");

    private final UUID id;
    private final String descricao;

    public static SexoEnum fromId(UUID id) {
        if (id == null) return null;

        return Arrays.stream(values())
                     .filter(sexo -> sexo.getId()
                                         .equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Sexo inválido para o ID: " + id));
    }
}
