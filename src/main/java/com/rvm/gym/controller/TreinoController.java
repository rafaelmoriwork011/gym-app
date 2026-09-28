package com.rvm.gym.controller;

import com.rvm.gym.dto.response.TreinoResponseDto;
import com.rvm.gym.dto.response.TreinosResponseDto;
import com.rvm.gym.service.TreinoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/treino")
@RequiredArgsConstructor
@Tag(name = "Treino", description = "Consulta e finalização de treinos")
public class TreinoController {

    private final TreinoService treinoService;

    @Operation(summary = "Finaliza um treino", description = "Marca o treino informado como finalizado.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Treino finalizado com sucesso", content = @Content), @ApiResponse(responseCode = "404", description = "Treino não encontrado", content = @Content)})
    @PostMapping("/finalizar/{treinoId}")
    public ResponseEntity<Void> finalizarTreino(@Parameter(description = "ID do treino", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID treinoId) {

        this.treinoService.finalizarTreino(treinoId);

        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "Busca o treino atual de uma configuração", description = "Retorna o treino atual (com seus exercícios) associado à configuração de treino.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Treino atual encontrado", content = @Content(schema = @Schema(implementation = TreinoResponseDto.class))), @ApiResponse(responseCode = "404", description = "Configuração de treino não encontrada", content = @Content)})
    @GetMapping("/buscar-treino-atual-por-configuracao/{treinoConfigId}")
    public ResponseEntity<TreinoResponseDto> buscarTreinoAtualPorConfiguracao(@Parameter(description = "ID da configuração de treino", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID treinoConfigId) {

        TreinoResponseDto treinoAtualResponseDto = this.treinoService.buscarTreinoAtual(treinoConfigId);
        return ResponseEntity.ok(treinoAtualResponseDto);
    }

    @Operation(summary = "Lista os treinos de uma configuração", description = "Retorna todos os treinos gerados para a configuração de treino informada.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Treinos listados com sucesso", content = @Content(schema = @Schema(implementation = TreinosResponseDto.class))), @ApiResponse(responseCode = "404", description = "Configuração de treino não encontrada", content = @Content)})
    @GetMapping("/treinos-por-configuracao/{treinoConfigId}")
    public ResponseEntity<TreinosResponseDto> visualizarTreinos(@Parameter(description = "ID da configuração de treino", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID treinoConfigId) {

        TreinosResponseDto treinosResponse = this.treinoService.visualizarTreinos(treinoConfigId);

        return ResponseEntity.ok(treinosResponse);
    }
}