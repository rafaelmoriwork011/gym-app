package com.rvm.gym.mapper.response;

import com.rvm.gym.dto.response.TreinoResponseDto;
import com.rvm.gym.dto.response.TreinosResponseDto;
import com.rvm.gym.entity.Treino;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {TreinoResponseDtoMapper.class})
public abstract class TreinosResponseDtoMapper {

    @Autowired
    private TreinoResponseDtoMapper treinoResponseDtoMapper;

    public TreinosResponseDto toTreinosResponseDto(List<Treino> treinos) {
        List<TreinoResponseDto> treinosResponseDto = new ArrayList<>();

        for (Treino treino : treinos) {
            TreinoResponseDto treinoResponseDto = this.treinoResponseDtoMapper.toTreinoResponseDto(treino);
            treinosResponseDto.add(treinoResponseDto);
        }

        return TreinosResponseDto.builder()
                .treinos(treinosResponseDto)
                .build();
    }
}
