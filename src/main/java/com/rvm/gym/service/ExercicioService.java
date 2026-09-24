package com.rvm.gym.service;

import com.rvm.gym.entity.Exercicio;
import com.rvm.gym.enums.GrupoMuscularEnum;
import com.rvm.gym.exception.BusinessException;
import com.rvm.gym.repository.ExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public Exercicio sortearExercicio(List<Exercicio> exerciciosJaSorteados, GrupoMuscularEnum grupoMuscularEnum) {

        List<UUID> uuidsJaSorteados = new ArrayList<>();
        for (Exercicio exercicioJaSorteado : exerciciosJaSorteados) {
            uuidsJaSorteados.add(exercicioJaSorteado.getId());
        }

        List<Exercicio> candidatos = this.exercicioRepository.findByGrupoMuscularAndIdNotIn(grupoMuscularEnum, uuidsJaSorteados);

        if (candidatos.isEmpty()) {
            throw new BusinessException("Exercicios insuficientes para a seleção.");
        }

        return candidatos.get(ThreadLocalRandom.current()
                                               .nextInt(candidatos.size()));
    }

}
