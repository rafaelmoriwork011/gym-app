package com.rvm.gym.service;

import com.rvm.gym.dto.internal.ConfiguracaoTreinoDto;
import com.rvm.gym.dto.request.ConfiguracaoTreinoRequestDto;
import com.rvm.gym.entity.Treino;
import com.rvm.gym.entity.TreinoConfiguracao;
import com.rvm.gym.entity.Usuario;
import com.rvm.gym.enums.TreinoConfiguracaoStatusEnum;
import com.rvm.gym.enums.TreinoObjetivoEnum;
import com.rvm.gym.exception.BusinessException;
import com.rvm.gym.mapper.internal.ConfiguracaoTreinoDtoMapper;
import com.rvm.gym.repository.TreinoConfiguracaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreinoConfiguracaoService {

    private final UsuarioService usuarioService;
    private final TreinoService treinoService;
    private final TreinoConfiguracaoRepository treinoConfiguracaoRepository;
    private final ConfiguracaoTreinoDtoMapper configuracaoTreinoDtoMapper;

    @Transactional
    public void configurar(ConfiguracaoTreinoRequestDto configuracaoTreinoRequestDto) {
        ConfiguracaoTreinoDto configuracaoTreinoDto = this.configuracaoTreinoDtoMapper.toConfiguracaoTreinoDto(configuracaoTreinoRequestDto);
        TreinoConfiguracao treinoConfig = this.gerarConfiguracao(configuracaoTreinoDto);
        treinoConfiguracaoRepository.save(treinoConfig);
    }


    private TreinoConfiguracao gerarConfiguracao(ConfiguracaoTreinoDto configuracaoTreinoDto) {
        Usuario usuario = this.usuarioService.findById(configuracaoTreinoDto.getUsuario());

        this.treinoConfiguracaoRepository.findByUsuarioAndStatus(usuario, TreinoConfiguracaoStatusEnum.ATIVO)
                                         .ifPresent(treinoConfig -> {
                                             throw new BusinessException("Já existe uma configuração de treino ativa para este usuário.");
                                         });

        TreinoObjetivoEnum objetivo = TreinoObjetivoEnum.fromId(configuracaoTreinoDto.getObjetivo());

        var treinoConfig = TreinoConfiguracao.builder()
                .usuario(usuario)
                .observacao(configuracaoTreinoDto.getObservacao())
                .objetivo(objetivo)
                .status(TreinoConfiguracaoStatusEnum.ATIVO)
                .build();


        List<Treino> treinos = this.treinoService.gerarTreinos(configuracaoTreinoDto.getMapeamentosTreinos(), treinoConfig.getObjetivo());
        treinoConfig.addTreinos(treinos);

        return treinoConfig;
    }
}





