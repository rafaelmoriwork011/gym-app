package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

@Getter
@AllArgsConstructor
public enum GrupoMuscularEnum {

    PEITO(UUID.fromString("42c6b78a-27b1-427f-a844-eee810b48084"), "Peito"),
    COSTAS(UUID.fromString("16cc86b4-0183-44c5-b200-7ea88ef47730"), "Costas"),
    OMBROS(UUID.fromString("d23c4e91-537f-4791-b280-fbc832f52044"), "Ombros"),
    BICEPS(UUID.fromString("c23db6a3-87d8-453a-b61e-19d188fde1f1"), "Bíceps"),
    TRICEPS(UUID.fromString("3c5b1050-092e-46d3-a2ed-3b79fa2524a5"), "Tríceps"),
    ANTEBRACO(UUID.fromString("cb91b12d-bd80-4449-af5f-87cdad75c9c7"), "Antebraço"),
    QUADRICEPS(UUID.fromString("1a2c9754-7550-49fd-8959-664848dccbd9"), "Quadríceps (Anterior da Coxa)"),
    POSTERIOR_COXA(UUID.fromString("81f65876-00c5-4222-bd54-c5beaeed9ebe"), "Posterior de Coxa (Isquiotibiais)"),
    GLUTEOS_QUADRIL(UUID.fromString("b220d05c-743f-43c8-9d7a-975f9c899555"), "Glúteos e Quadril"),
    PARTE_INTERNA_COXA(UUID.fromString("4832bee7-61c1-4621-9673-0a62d38e17df"), "Parte Interna da Coxa"),
    PANTURRILHAS(UUID.fromString("7a0d9a2b-a464-4d25-b3ac-68470d7d0ea7"), "Panturrilhas"),
    ABDOMEN(UUID.fromString("508b31b8-ea78-4ff2-800a-f0188b683f46"), "Abdômen (Core)");


    private final UUID id;
    private final String descricao;

    public static GrupoMuscularEnum fromId(UUID id) {
        if (id == null) {
            return null;
        }

        return Arrays.stream(values())
                     .filter(grupoMuscular -> grupoMuscular.getId()
                                                           .equals(id))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Grupo muscular inválido para o ID: " + id));
    }
}
