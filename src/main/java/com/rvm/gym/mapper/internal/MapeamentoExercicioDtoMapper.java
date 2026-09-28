package com.rvm.gym.mapper.internal;

import com.rvm.gym.dto.internal.MapeamentoExercicioDto;
import com.rvm.gym.dto.request.MapeamentoExercicioRequestDto;
import com.rvm.gym.entity.TreinoExercicio;
import com.rvm.gym.enums.GrupoMuscularEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

@Mapper(componentModel = "spring")
public abstract class MapeamentoExercicioDtoMapper {

    @Mapping(target = "grupoMuscular", source = "grupoMuscularId")
    public abstract MapeamentoExercicioDto toMapeamentoExercicioDto(MapeamentoExercicioRequestDto mapeamentoExercicioDto);

    protected GrupoMuscularEnum map(UUID id) {
        return GrupoMuscularEnum.fromId(id);
    }

    public List<MapeamentoExercicioDto> toMapeamentoExercicioDtoList(List<TreinoExercicio> treinoExercicios) {

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
