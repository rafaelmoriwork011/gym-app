package com.rvm.gym.service;

import com.rvm.gym.dto.internal.MapeamentoExercicioDto;
import com.rvm.gym.dto.internal.MapeamentoTreinoDto;
import com.rvm.gym.dto.response.ExercicioResponseDto;
import com.rvm.gym.dto.response.TreinoResponseDto;
import com.rvm.gym.dto.response.TreinosResponseDto;
import com.rvm.gym.entity.*;
import com.rvm.gym.enums.GrupoMuscularEnum;
import com.rvm.gym.enums.TreinoConfiguracaoStatusEnum;
import com.rvm.gym.enums.TreinoObjetivoEnum;
import com.rvm.gym.enums.TreinoStatusEnum;
import com.rvm.gym.exception.BusinessException;
import com.rvm.gym.repository.TreinoConfiguracaoRepository;
import com.rvm.gym.repository.TreinoExecucaoRepository;
import com.rvm.gym.repository.TreinoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final ExercicioService exercicioService;
    private final TreinoRepository treinoRepository;
    private final TreinoExecucaoRepository treinoExecucaoRepository;
    private final TreinoConfiguracaoRepository treinoConfiguracaoRepository;

    @Transactional
    public void finalizarTreino(UUID treinoId) {
        Treino treino = this.treinoRepository.findById(treinoId)
                                             .orElseThrow(() -> new EntityNotFoundException("Treino não encontrado"));

        if (treino.getTreinoConfiguracao()
                  .getStatus() != TreinoConfiguracaoStatusEnum.ATIVO) {
            throw new BusinessException("O treino não pode ser finalizado, pois o treino configuracao não está ativo");
        }

        var treinoExecucao = TreinoExecucao.builder()
                .treino(treino)
                .treinoStatus(TreinoStatusEnum.FINALIZADO)
                .dataOcorrencia(LocalDateTime.now())
                .build();

        this.treinoExecucaoRepository.save(treinoExecucao);
    }

    public List<Treino> gerarTreinos(List<MapeamentoTreinoDto> mapeamentosTreinos, TreinoObjetivoEnum treinoObjetivo) {
        List<Treino> treinos = new ArrayList<>();

        if (mapeamentosTreinos.isEmpty()) {
            throw new BusinessException("Não foi encontrado nenhum mapeamento de treino para gerar os treinos");
        }

        for (MapeamentoTreinoDto mapeamentoTreinoDto : mapeamentosTreinos) {

            var treino = Treino.builder()
                    .nome(mapeamentoTreinoDto.getNome())
                    .ordem(mapeamentoTreinoDto.getOrdem())
                    .build();

            List<TreinoExercicio> treinoExercicios = this.gerarTreinoExercicios(mapeamentoTreinoDto.getMapeamentosExercicios(), treinoObjetivo);
            treino.addTreinoExercicios(treinoExercicios);
            treinos.add(treino);
        }

        return treinos;
    }

    private List<TreinoExercicio> gerarTreinoExercicios(List<MapeamentoExercicioDto> mapeamentosExerciciosDto, TreinoObjetivoEnum treinoObjetivo) {

        if (mapeamentosExerciciosDto.isEmpty()) {
            throw new BusinessException("Não foi encontrado nenhum mapeamento de exercicio para gerar os exercicios para os treinos");
        }

        List<TreinoExercicio> treinoExercicios = new ArrayList<>();
        List<Exercicio> exerciciosSorteados = new ArrayList<>();

        TreinoExercicio treinoExercicioAnterior = null;
        for (MapeamentoExercicioDto mapeamentoExercicioDto : mapeamentosExerciciosDto) {

            for (int i = 0; i < mapeamentoExercicioDto.getQuantidadeExercicios(); i++) {
                Exercicio exercicio = this.exercicioService.sortearExercicio(exerciciosSorteados, mapeamentoExercicioDto.getGrupoMuscular());
                exerciciosSorteados.add(exercicio);

                var treinoExercicio = TreinoExercicio.builder()
                        .exercicio(exercicio)
                        .build();

                treinoExercicio.configurarTreinoExercicioPorObjetivo(treinoObjetivo, treinoExercicioAnterior);

                treinoExercicios.add(treinoExercicio);
                treinoExercicioAnterior = treinoExercicio;
            }

        }

        return treinoExercicios;
    }

    public MapeamentoTreinoDto gerarMapeamentoTreinoDtoDeUmTreinoExistente(Treino treino) {
        var mapeamentoTreinoDto = MapeamentoTreinoDto.builder()
                .nome(treino.getNome())
                .ordem(treino.getOrdem())
                .build();


        List<MapeamentoExercicioDto> mapeamentosExerciciosDto = this.gerarMapeamentoExercicioDtoDeUmTreinoExercicioExistente(treino.getTreinoExercicios());
        mapeamentoTreinoDto.setMapeamentosExercicios(mapeamentosExerciciosDto);

        return mapeamentoTreinoDto;
    }

    private List<MapeamentoExercicioDto> gerarMapeamentoExercicioDtoDeUmTreinoExercicioExistente(List<TreinoExercicio> treinoExercicios) {

        //O set garante que não deve repetir os grupos musculares
        Set<GrupoMuscularEnum> gruposMusculares = new HashSet<>();

        List<MapeamentoExercicioDto> mapeamentosExerciciosDto = new ArrayList<>();

        for (TreinoExercicio treinoExercicio : treinoExercicios) {
            gruposMusculares.add(treinoExercicio.getExercicio()
                                                .getGrupoMuscular());
        }

        for (GrupoMuscularEnum grupoMuscular : gruposMusculares) {

            var novoMapeamentoExercicioDto = MapeamentoExercicioDto.builder()
                    .grupoMuscular(grupoMuscular)
                    .build();

            mapeamentosExerciciosDto.add(novoMapeamentoExercicioDto);

            for (TreinoExercicio treinoExercicio : treinoExercicios) {

                if (treinoExercicio.getExercicio()
                                   .getGrupoMuscular() == grupoMuscular) {
                    novoMapeamentoExercicioDto.incrementarQuantidadeExercicios();
                }

            }
        }

        return mapeamentosExerciciosDto;
    }

    public TreinoResponseDto buscarTreinoAtual(UUID treinoConfiguracaoId) {

        TreinoConfiguracao treinoConfig = this.treinoConfiguracaoRepository.findById(treinoConfiguracaoId)
                                                                           .orElseThrow(() -> new EntityNotFoundException("Configuração não encontrado"));

        if (treinoConfig.getStatus() != TreinoConfiguracaoStatusEnum.ATIVO) {
            throw new BusinessException("Esta configuração de treino não está ativa");
        }

        Treino proximoTreino = this.buscarProximoTreino(treinoConfig);

        var treinoAtualResponseDto = TreinoResponseDto.builder()
                .id(proximoTreino.getId())
                .nome(proximoTreino.getNome())
                .build();

        for (TreinoExercicio treinoExercicio : proximoTreino.getTreinoExercicios()) {
            var exercicioDto = ExercicioResponseDto.builder()
                    .nome(treinoExercicio.getExercicio()
                                         .getNome())
                    .grupoMuscular(treinoExercicio.getExercicio()
                                                  .getGrupoMuscular()
                                                  .getDescricao())
                    .build();

            treinoAtualResponseDto.addExercicio(exercicioDto);
        }

        return treinoAtualResponseDto;
    }

    private Treino buscarProximoTreino(TreinoConfiguracao treinoConfig) {
        List<UUID> idsTreinos = treinoConfig.getTreinos()
                                            .stream()
                                            .map(Treino::getId)
                                            .toList();

        var treinoExecucao = this.treinoExecucaoRepository.findFirstByTreinoIdInOrderByDataOcorrenciaDesc(idsTreinos);

        List<Treino> treinosOrdenados = treinoConfig.getTreinosOrdenados();
        if (treinoExecucao == null) {
            return treinosOrdenados.getFirst();
        }


        Treino ultimoTreinoExecutado = treinoExecucao.getTreino();

        int index = treinosOrdenados.indexOf(ultimoTreinoExecutado);

        int proximoIndex = index + 1;
        int ultumoIndexDisponivel = treinosOrdenados.size() - 1;
        if (proximoIndex <= ultumoIndexDisponivel) {
            return treinosOrdenados.get(proximoIndex);
        }

        return treinosOrdenados.getFirst();
    }

    public TreinosResponseDto visualizarTreinos(UUID treinoConfiguracaoId) {

        TreinoConfiguracao treinoConfig = this.treinoConfiguracaoRepository.findById(treinoConfiguracaoId)
                                                                           .orElseThrow(() -> new EntityNotFoundException("Configuração não encontrado"));
        List<TreinoResponseDto> treinosResponseDto = new ArrayList<>();

        for (Treino treino : treinoConfig.getTreinos()) {
            var treinoResponseDto = TreinoResponseDto.builder()
                    .id(treino.getId())
                    .nome(treino.getNome())
                    .build();

            for (TreinoExercicio treinoExercicio : treino.getTreinoExercicios()) {
                var exercicioDto = ExercicioResponseDto.builder()
                        .nome(treinoExercicio.getExercicio()
                                             .getNome())
                        .grupoMuscular(treinoExercicio.getExercicio()
                                                      .getGrupoMuscular()
                                                      .getDescricao())
                        .build();

                treinoResponseDto.addExercicio(exercicioDto);
            }

            treinosResponseDto.add(treinoResponseDto);
        }


        return TreinosResponseDto.builder()
                .treinos(treinosResponseDto)
                .build();
    }
}
