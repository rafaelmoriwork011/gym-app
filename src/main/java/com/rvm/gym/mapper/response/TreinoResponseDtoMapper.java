package com.rvm.gym.mapper.response;

import com.rvm.gym.dto.response.TreinoResponseDto;
import com.rvm.gym.entity.Treino;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExercicioResponseDtoMapper.class})
public abstract class TreinoResponseDtoMapper {

    @Mapping(target = "exercicios", source = "treinoExercicios")
    public abstract TreinoResponseDto toTreinoResponseDto(Treino treino);

}
