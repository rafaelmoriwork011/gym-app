package com.rvm.gym.entity;

import com.rvm.gym.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "nome", length = 1, nullable = false)
    @EqualsAndHashCode.Include
    private String nome;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TreinoExercicio> treinoExercicios = new ArrayList<>();

    public void addTreinoExercicios(List<TreinoExercicio> treinoExercicios) {

        for (TreinoExercicio treinoExercicio : treinoExercicios) {
            if (this.treinoExercicios.contains(treinoExercicio)) {
                throw new BusinessException("Existem treino exercicios duplicados na lista do treino " + this.nome);
            }

            this.treinoExercicios.add(treinoExercicio);
            treinoExercicio.setTreino(this);
        }
    }

}
