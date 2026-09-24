package com.rvm.gym.mapper.internal;

import com.rvm.gym.dto.internal.MapeamentoTreinoDto;
import com.rvm.gym.dto.request.MapeamentoTreinoRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MapeamentoExercicioDtoMapper.class})
public interface MapeamentoTreinoDtoMapper {

    MapeamentoTreinoDto toMapeamentoTreinoDto(MapeamentoTreinoRequestDto mapeamentoTreinoDto);
}
