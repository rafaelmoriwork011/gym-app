package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum EstimuloEnum {

    HIPERTROFIA(UUID.fromString("b0000000-0000-4000-8000-000000000001"), "Hipertrofia"),
    FORCA(UUID.fromString("b0000000-0000-4000-8000-000000000002"), "Força"),
    RESISTENCIA(UUID.fromString("b0000000-0000-4000-8000-000000000003"), "Resistência");

    private final UUID id;
    private final String descricao;

    public static EstimuloEnum fromId(UUID id) {
        if (id == null) return null;

        return Arrays.stream(values())
                     .filter(estimulo -> estimulo.getId()
                                                 .equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Estímulo inválido para o ID: " + id));
    }
}
