package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum TreinoConfiguracaoStatusEnum {

    ATIVO(UUID.fromString("e0000000-0000-4000-8000-000000000001"), "Ativo"),
    FINALIZADO(UUID.fromString("e0000000-0000-4000-8000-000000000002"), "Finalizado");

    private final UUID id;
    private final String descricao;

    public static TreinoConfiguracaoStatusEnum fromId(UUID id) {
        if (id == null) return null;

        return Arrays.stream(values())
                     .filter(status -> status.getId().equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Status de configuração de treino inválido para o ID: " + id));
    }
}