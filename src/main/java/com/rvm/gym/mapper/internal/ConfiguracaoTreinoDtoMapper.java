package com.rvm.gym.mapper.internal;

import com.rvm.gym.dto.internal.ConfiguracaoTreinoDto;
import com.rvm.gym.dto.request.ConfiguracaoTreinoRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MapeamentoTreinoDtoMapper.class})
public interface ConfiguracaoTreinoDtoMapper {

    ConfiguracaoTreinoDto toConfiguracaoTreinoDto(ConfiguracaoTreinoRequestDto configuracaoTreinoRequestDto);
}
