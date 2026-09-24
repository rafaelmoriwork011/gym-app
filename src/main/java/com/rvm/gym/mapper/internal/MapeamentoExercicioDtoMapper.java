package com.rvm.gym.mapper.internal;

import com.rvm.gym.dto.internal.MapeamentoExercicioDto;
import com.rvm.gym.dto.request.MapeamentoExercicioRequestDto;
import com.rvm.gym.enums.GrupoMuscularEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface MapeamentoExercicioDtoMapper {

    @Mapping(target = "grupoMuscular", source = "grupoMuscularId")
    MapeamentoExercicioDto toMapeamentoExercicioDto(MapeamentoExercicioRequestDto mapeamentoExercicioDto);

    default GrupoMuscularEnum map(UUID id) {
        return GrupoMuscularEnum.fromId(id);
    }

}
