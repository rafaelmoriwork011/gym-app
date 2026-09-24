package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum TreinoConfiguracaoStatusEnum {

    ATIVO(UUID.fromString("68416787-ff4b-4cfb-9a38-2e18ce20e40e"), "Ativo"),
    FINALIZADO(UUID.fromString("a54984eb-883f-4d84-8277-b9e48f4780d9"), "Finalizado");

    private final UUID id;
    private final String descricao;

    public static TreinoConfiguracaoStatusEnum fromId(UUID id) {
        if (id == null) {
            return null;
        }

        return Arrays.stream(values())
                     .filter(status -> status.getId()
                                             .equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Status de configuração de treino inválido para o ID: " + id));
    }
}