package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum EstimuloEnum {

    HIPERTROFIA(UUID.fromString("46ec97fe-daa8-40a2-8380-f184401daeaa"), "Hipertrofia", 4, 12, Duration.ofMinutes(2)),
    FORCA(UUID.fromString("0b546bc7-0c6d-4334-b6ff-f5e60036d5d5"), "Força", 4, 6, Duration.ofMinutes(3)),
    RESISTENCIA(UUID.fromString("63565e3f-25b4-4757-9ab6-c2b24ef84707"), "Resistência", 4, 20, Duration.ofSeconds(30));

    private final UUID id;
    private final String descricao;
    private final int series;
    private final int repeticoes;
    private final Duration tempoDescanso;

    public static EstimuloEnum fromId(UUID id) {
        if (id == null) {
            return null;
        }

        return Arrays.stream(values())
                     .filter(estimulo -> estimulo.getId()
                                                 .equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Estímulo inválido para o ID: " + id));
    }
}
