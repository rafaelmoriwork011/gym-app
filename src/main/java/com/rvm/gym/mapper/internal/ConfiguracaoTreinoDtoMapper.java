package com.rvm.gym.mapper.internal;

import com.rvm.gym.dto.internal.ConfiguracaoTreinoDto;
import com.rvm.gym.dto.request.ConfiguracaoTreinoRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MapeamentoTreinoDtoMapper.class})
public abstract class ConfiguracaoTreinoDtoMapper {

    public abstract ConfiguracaoTreinoDto toConfiguracaoTreinoDto(ConfiguracaoTreinoRequestDto configuracaoTreinoRequestDto);
}
