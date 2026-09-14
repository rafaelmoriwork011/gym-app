package com.rvm.gym.entity;

import com.rvm.gym.enums.TreinoConfiguracaoStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "treino_configuracao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TreinoConfiguracao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "quantidade_treino_semana", nullable = false)
    private Integer quantidadeTreinoSemana;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @CreationTimestamp
    @Column(name = "data_inclusao", nullable = false, updatable = false)
    private LocalDateTime dataInclusao;

    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "treino_configuracao_status_id", nullable = false)
    private TreinoConfiguracaoStatusEnum status;

    @OneToMany(mappedBy = "configuracaoTreino", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TreinoConfiguracaoObjetivo> objetivos = new ArrayList<>();
}
