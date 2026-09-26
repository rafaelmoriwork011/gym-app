package com.rvm.gym.service;

import com.rvm.gym.dto.internal.MapeamentoExercicioDto;
import com.rvm.gym.dto.internal.MapeamentoTreinoDto;
import com.rvm.gym.entity.Exercicio;
import com.rvm.gym.entity.Treino;
import com.rvm.gym.entity.TreinoExercicio;
import com.rvm.gym.enums.GrupoMuscularEnum;
import com.rvm.gym.enums.TreinoObjetivoEnum;
import com.rvm.gym.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final ExercicioService exercicioService;

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

    //TODO: Refatorar & otimizar
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
}
