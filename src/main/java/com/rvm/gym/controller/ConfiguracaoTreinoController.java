package com.rvm.gym.controller;

import com.rvm.gym.dto.request.ConfiguracaoTreinoRequestDto;
import com.rvm.gym.dto.response.ValidacaoTreinoRenovacaoResponseDto;
import com.rvm.gym.service.TreinoConfiguracaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/configurar-treino")
@RequiredArgsConstructor
@Tag(name = "Configuração de Treino", description = "Criação, reconfiguração e verificação de renovação da configuração de treino de um usuário")
public class ConfiguracaoTreinoController {

    private final TreinoConfiguracaoService configuracaoTreinoService;

    @Operation(summary = "Configura o treino de um usuário", description = "Cria a configuração de treino do usuário com base no objetivo e nos mapeamentos de grupos musculares por treino informados.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Treino configurado com sucesso", content = @Content), @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content), @ApiResponse(responseCode = "404", description = "Usuário ou objetivo não encontrado", content = @Content)})
    @PostMapping("/configurar")
    public ResponseEntity<Void> configurar(@RequestBody @Valid ConfiguracaoTreinoRequestDto configuracaoTreinoRequestDto) {

        configuracaoTreinoService.configurar(configuracaoTreinoRequestDto);

        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "Reconfigura o treino de um usuário", description = "Gera novamente os treinos a partir da configuração existente do usuário.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Treino reconfigurado com sucesso", content = @Content), @ApiResponse(responseCode = "404", description = "Configuração de treino não encontrada", content = @Content)})
    @PostMapping("/reconfigurar/{id}")
    public ResponseEntity<Void> reconfigurar(@Parameter(description = "ID da configuração de treino", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID id) {

        configuracaoTreinoService.reconfigurarTreinoDeUsuario(id);

        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "Verifica se o treino deve ser renovado", description = "Indica se a configuração de treino atingiu o critério de renovação.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verificação realizada com sucesso", content = @Content(schema = @Schema(implementation = ValidacaoTreinoRenovacaoResponseDto.class))), @ApiResponse(responseCode = "404", description = "Configuração de treino não encontrada", content = @Content)})
    @GetMapping("/verificar-renovacao/{id}")
    public ResponseEntity<ValidacaoTreinoRenovacaoResponseDto> verificarRenovacao(@Parameter(description = "ID da configuração de treino", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID id) {

        ValidacaoTreinoRenovacaoResponseDto response = this.configuracaoTreinoService.verificarSeTreinoDeveRenovar(id);

        return ResponseEntity.ok(response);
    }
}