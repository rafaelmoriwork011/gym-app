package com.rvm.gym.entity;

import com.rvm.gym.enums.TreinoObjetivoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "treino_configuracao_objetivo", uniqueConstraints = @UniqueConstraint(name = "uq_treino_configuracao_objetivo", columnNames = {"configuracao_treino_id", "treino_objetivo_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TreinoConfiguracaoObjetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "treino_objetivo_id", nullable = false)
    private TreinoObjetivoEnum treinoObjetivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "configuracao_treino_id", nullable = false)
    private TreinoConfiguracao configuracaoTreino;
}