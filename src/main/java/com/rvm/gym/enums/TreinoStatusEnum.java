package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum TreinoStatusEnum {

    FINALIZADO(UUID.fromString("a96f1ca5-ed23-46f0-9595-7f3e14524c32"), "Finalizado"),
    DISPENSADO(UUID.fromString("77dda09a-dcba-4c03-aa52-acb0945d036f"), "Dispensado");

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
