package com.rvm.gym.entity;

import com.rvm.gym.enums.EstimuloEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "treino_exercicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TreinoExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @Column(name = "estimulo_id", nullable = false)
    private EstimuloEnum estimulo;

    // Nullable no banco - equipamento é opcional para o exercício dentro do treino
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "repeticoes", nullable = false)
    private Integer repeticoes;

    @Column(name = "tempo_descanso", nullable = false)
    private LocalTime tempoDescanso;
}
