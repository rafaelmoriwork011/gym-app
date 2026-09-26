package com.rvm.gym.entity;

import com.rvm.gym.enums.TreinoConfiguracaoStatusEnum;
import com.rvm.gym.enums.TreinoObjetivoEnum;
import com.rvm.gym.enums.TreinoRenovacaoLimite;
import com.rvm.gym.exception.BusinessException;
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
    @EqualsAndHashCode.Include
    private Usuario usuario;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "data_fim", nullable = false, updatable = false)
    private LocalDateTime dataFim;

    @CreationTimestamp
    @Column(name = "data_inclusao", nullable = false, updatable = false)
    private LocalDateTime dataInclusao;

    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "treino_configuracao_status_id", nullable = false)
    private TreinoConfiguracaoStatusEnum status;

    @Column(name = "treino_objetivo_id", nullable = false)
    private TreinoObjetivoEnum objetivo;

    @OneToMany(mappedBy = "treinoConfiguracao", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Treino> treinos = new ArrayList<>();


    public void addTreinos(List<Treino> treinos) {
        for (Treino treino : treinos) {
            this.verificaDuplicidadeTreinoParaAdicionarAtreinos(treino);
            this.treinos.add(treino);
            treino.setTreinoConfiguracao(this);
        }
    }

    private void verificaDuplicidadeTreinoParaAdicionarAtreinos(Treino treino) {
        for (Treino treinoConfig : this.treinos) {
            if (treinoConfig.getOrdem() == treino.getOrdem()) {
                throw new BusinessException("A ordem de treino: " + treino.getOrdem() + " está duplicado");
            }

            if (treinoConfig.getNome() == treino.getNome()) {
                throw new BusinessException("O nome de treino: " + treino.getNome() + " está duplicado");
            }
        }
    }

    public void configuraDataFim() {
        Integer quantidadeDiasRenovacao = TreinoRenovacaoLimite.QTD_DIAS_PARA_RENOVAR_CONFIGURACAO.getQuantidadeDiasParaRenovarConfiguracao();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        LocalDateTime dataFim = dataHoraAtual.plusDays(quantidadeDiasRenovacao);
        this.setDataFim(dataFim);
    }

    public boolean deveRenovar() {

        if (this.status == TreinoConfiguracaoStatusEnum.FINALIZADO) {
            return true;
        }

        LocalDateTime dataFim = this.getDataFim();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        return dataFim.isBefore(dataHoraAtual);
    }

    public void finalizar() {
        this.status = TreinoConfiguracaoStatusEnum.FINALIZADO;
    }

    public void atualizarDataAlteracao() {
        this.dataAlteracao = LocalDateTime.now();
    }

}
