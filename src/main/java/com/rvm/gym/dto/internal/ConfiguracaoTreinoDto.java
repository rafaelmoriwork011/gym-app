package com.rvm.gym.dto.internal;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfiguracaoTreinoDto {

    private UUID usuario;
    private String observacao;
    private UUID objetivo;
    private List<MapeamentoTreinoDto> mapeamentosTreinos;
}
