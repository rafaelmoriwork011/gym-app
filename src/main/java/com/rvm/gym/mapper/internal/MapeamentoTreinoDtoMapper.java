package com.rvm.gym.mapper.internal;

import com.rvm.gym.dto.internal.MapeamentoExercicioDto;
import com.rvm.gym.dto.internal.MapeamentoTreinoDto;
import com.rvm.gym.dto.request.MapeamentoTreinoRequestDto;
import com.rvm.gym.entity.Treino;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MapeamentoExercicioDtoMapper.class})
public abstract class MapeamentoTreinoDtoMapper {

    @Autowired
    private MapeamentoExercicioDtoMapper mapeamentoExercicioDtoMapper;

    public abstract MapeamentoTreinoDto toMapeamentoTreinoDto(MapeamentoTreinoRequestDto mapeamentoTreinoDto);

    public MapeamentoTreinoDto toMapeamentoTreinoDto(Treino treino) {
        var mapeamentoTreinoDto = MapeamentoTreinoDto.builder()
                .nome(treino.getNome())
                .ordem(treino.getOrdem())
                .build();


        List<MapeamentoExercicioDto> mapeamentosExerciciosDto = this.mapeamentoExercicioDtoMapper.toMapeamentoExercicioDtoList(treino.getTreinoExercicios());
        mapeamentoTreinoDto.setMapeamentosExercicios(mapeamentosExerciciosDto);

        return mapeamentoTreinoDto;
    }

}
