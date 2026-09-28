package com.rvm.gym.mapper.response;

import com.rvm.gym.dto.response.TreinoResponseDto;
import com.rvm.gym.entity.Treino;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExercicioResponseDtoMapper.class})
public interface TreinoResponseDtoMapper {

    @Mapping(target = "exercicios", source = "treinoExercicios")
    TreinoResponseDto toTreinoResponseDto(Treino treino);

}
