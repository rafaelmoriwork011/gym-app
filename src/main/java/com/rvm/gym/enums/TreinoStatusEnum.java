package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum TreinoStatusEnum {

    FINALIZADO(UUID.fromString("d0000000-0000-4000-8000-000000000001"), "Finalizado"),
    DISPENSADO(UUID.fromString("d0000000-0000-4000-8000-000000000002"), "Dispensado");

    private final UUID id;
    private final String descricao;

    public static TreinoStatusEnum fromId(UUID id) {
        if (id == null) return null;

        return Arrays.stream(values())
                     .filter(status -> status.getId()
                                             .equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Status de treino inválido para o ID: " + id));
    }
}
