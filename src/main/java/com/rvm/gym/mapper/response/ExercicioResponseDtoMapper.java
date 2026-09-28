package com.rvm.gym.mapper.response;

import com.rvm.gym.dto.response.ExercicioResponseDto;
import com.rvm.gym.entity.TreinoExercicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExercicioResponseDtoMapper {

    @Mapping(target = "nome", source = "exercicio.nome")
    @Mapping(target = "grupoMuscular", source = "exercicio.grupoMuscular.descricao")
    ExercicioResponseDto toExercicioResponseDto(TreinoExercicio exercicio);

}
