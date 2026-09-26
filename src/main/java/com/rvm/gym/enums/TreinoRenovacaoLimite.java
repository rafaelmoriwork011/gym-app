package com.rvm.gym.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TreinoRenovacaoLimite {

    QTD_DIAS_PARA_RENOVAR_CONFIGURACAO(90);

    private final Integer quantidadeDiasParaRenovarConfiguracao;
}
