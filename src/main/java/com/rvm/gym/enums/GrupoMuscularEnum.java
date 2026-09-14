package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum GrupoMuscularEnum {

    PEITORAL(UUID.fromString("c0000000-0000-4000-8000-000000000001"), "Peitoral"),
    COSTAS(UUID.fromString("c0000000-0000-4000-8000-000000000002"), "Costas"),
    QUADRICEPS(UUID.fromString("c0000000-0000-4000-8000-000000000003"), "Quadríceps"),
    POSTERIOR_DE_COXA(UUID.fromString("c0000000-0000-4000-8000-000000000004"), "Posterior de Coxa"),
    GLUTEOS(UUID.fromString("c0000000-0000-4000-8000-000000000005"), "Glúteos"),
    OMBROS(UUID.fromString("c0000000-0000-4000-8000-000000000006"), "Ombros"),
    BICEPS(UUID.fromString("c0000000-0000-4000-8000-000000000007"), "Bíceps"),
    TRICEPS(UUID.fromString("c0000000-0000-4000-8000-000000000008"), "Tríceps"),
    PANTURRILHAS(UUID.fromString("c0000000-0000-4000-8000-000000000009"), "Panturrilhas"),
    ABDOMEN(UUID.fromString("c0000000-0000-4000-8000-000000000010"), "Abdômen"),
    ANTEBRACOS(UUID.fromString("c0000000-0000-4000-8000-000000000011"), "Antebraços"),
    ADUTORES(UUID.fromString("c0000000-0000-4000-8000-000000000012"), "Adutores");

    private final UUID id;
    private final String descricao;

    public static GrupoMuscularEnum fromId(UUID id) {
        if (id == null) return null;

        return Arrays.stream(values())
                     .filter(grupoMuscular -> grupoMuscular.getId()
                                                           .equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Grupo muscular inválido para o ID: " + id));
    }
}
