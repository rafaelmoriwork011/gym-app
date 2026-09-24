package com.rvm.gym.entity;

import com.rvm.gym.enums.EstimuloEnum;
import com.rvm.gym.enums.TreinoObjetivoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Duration;
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
    @EqualsAndHashCode.Include
    private Treino treino;

    @Column(name = "estimulo_id", nullable = false)
    private EstimuloEnum estimulo;

    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "repeticoes", nullable = false)
    private Integer repeticoes;

    @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
    @Column(name = "tempo_descanso", nullable = false)
    private Duration tempoDescanso;

    private void configurarTreinoExercicioPorEstimulo(EstimuloEnum estimulo) {
        this.estimulo = estimulo;
        this.series = estimulo.getSeries();
        this.repeticoes = estimulo.getRepeticoes();
        this.tempoDescanso = estimulo.getTempoDescanso();
    }

    private void configurarTreinoExercicioParaAumentoMassaMuscular(TreinoExercicio treinoExercicioAnterior) {

        if (treinoExercicioAnterior == null || treinoExercicioAnterior.getEstimulo() == EstimuloEnum.FORCA) {
            this.configurarTreinoExercicioPorEstimulo(EstimuloEnum.HIPERTROFIA);

            return;
        }

        this.configurarTreinoExercicioPorEstimulo(EstimuloEnum.FORCA);
    }

    public void configurarTreinoExercicioPorObjetivo(TreinoObjetivoEnum objetivo, TreinoExercicio treinoExercicioAnterior) {
        switch (objetivo) {
            case CONDICIONAMENTO_FISICO -> configurarTreinoExercicioPorEstimulo(EstimuloEnum.RESISTENCIA);
            case AUMENTO_MASSA_MUSCULAR -> configurarTreinoExercicioParaAumentoMassaMuscular(treinoExercicioAnterior);
        }
    }

}
