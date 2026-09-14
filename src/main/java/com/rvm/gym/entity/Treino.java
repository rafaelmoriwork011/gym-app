package com.rvm.gym.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "treino")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treino_configuracao_id", nullable = false)
    private TreinoConfiguracao treinoConfiguracao;

    // Mapeado como CHAR(1) no banco (ex: "A", "B", "C")
    @Column(name = "nome", length = 1, nullable = false, columnDefinition = "CHAR(1)")
    private String nome;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

}
