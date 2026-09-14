package com.rvm.gym.entity;

import com.rvm.gym.enums.TreinoStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "treino_execucao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TreinoExecucao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @Column(name = "treino_status_id", nullable = false)
    private TreinoStatusEnum treinoStatus;

    @Column(name = "data_ocorrencia", nullable = false)
    private LocalDateTime dataOcorrencia;
}
