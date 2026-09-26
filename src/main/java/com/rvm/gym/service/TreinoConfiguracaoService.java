package com.rvm.gym.service;

import com.rvm.gym.dto.internal.ConfiguracaoTreinoDto;
import com.rvm.gym.dto.internal.MapeamentoTreinoDto;
import com.rvm.gym.dto.request.ConfiguracaoTreinoRequestDto;
import com.rvm.gym.dto.response.ValidacaoTreinoRenovacaoResponseDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Transactional
    public void reconfigurarTreinoDeUsuario(UUID idTreinoAtual) {

        TreinoConfiguracao treinoConfigAtual = this.findTreinoConfiguracaoByTreinoId(idTreinoAtual);

        if (treinoConfigAtual.getStatus() != TreinoConfiguracaoStatusEnum.ATIVO) {
            throw new BusinessException("Para reconfigurar, é necessário um id de configuração de treino ativo");
        }

        TreinoObjetivoEnum objetivo = treinoConfigAtual.getObjetivo()
                                                       .getObjetivoDiferente();

        ConfiguracaoTreinoDto novoTreinoConfigDto = ConfiguracaoTreinoDto.builder()
                .usuario(treinoConfigAtual.getUsuario()
                                          .getId())
                .objetivo(objetivo.getId())
                .observacao(treinoConfigAtual.getObservacao())
                .build();

        List<MapeamentoTreinoDto> mapeamentoTreinosDto = new ArrayList<>();
        for (Treino treino : treinoConfigAtual.getTreinos()) {
            MapeamentoTreinoDto mapeamentoTreinoDto = this.treinoService.gerarMapeamentoTreinoDtoDeUmTreinoExistente(treino);
            mapeamentoTreinosDto.add(mapeamentoTreinoDto);
        }
        novoTreinoConfigDto.setMapeamentosTreinos(mapeamentoTreinosDto);

        treinoConfigAtual.finalizar();
        treinoConfigAtual.atualizarDataAlteracao();
        TreinoConfiguracao treinoConfigGerado = this.gerarConfiguracao(novoTreinoConfigDto);
        this.treinoConfiguracaoRepository.save(treinoConfigGerado);
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

        treinoConfig.configuraDataFim();

        List<Treino> treinos = this.treinoService.gerarTreinos(configuracaoTreinoDto.getMapeamentosTreinos(), treinoConfig.getObjetivo());
        treinoConfig.addTreinos(treinos);

        return treinoConfig;
    }

    public ValidacaoTreinoRenovacaoResponseDto verificarSeTreinoDeveRenovar(UUID treinoId) {
        TreinoConfiguracao treinoConfig = this.findTreinoConfiguracaoByTreinoId(treinoId);
        boolean deveRenovar = treinoConfig.deveRenovar();

        var validacaoTreinoRenovacaoResponseDto = ValidacaoTreinoRenovacaoResponseDto.builder()
                .deveRenovar(deveRenovar)
                .build();

        return validacaoTreinoRenovacaoResponseDto;
    }

    private TreinoConfiguracao findTreinoConfiguracaoByTreinoId(UUID treinoId) {
        return this.treinoConfiguracaoRepository.findById(treinoId)
                                                .orElseThrow(() -> new BusinessException("Treino não encontrado."));
    }

}
