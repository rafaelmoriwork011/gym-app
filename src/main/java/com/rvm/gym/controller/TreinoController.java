package com.rvm.gym.controller;

import com.rvm.gym.dto.response.TreinoResponseDto;
import com.rvm.gym.dto.response.TreinosResponseDto;
import com.rvm.gym.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/treino")
@RequiredArgsConstructor
public class TreinoController {

    private final TreinoService treinoService;

    @PostMapping("/finalizar/{treinoId}")
    public ResponseEntity finalizarTreino(@PathVariable UUID treinoId) {

        this.treinoService.finalizarTreino(treinoId);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/buscar-treino-atual-por-configuracao/{treinoConfigId}")
    public ResponseEntity<TreinoResponseDto> buscarTreinoAtualPorConfiguracao(@PathVariable UUID treinoConfigId) {

        TreinoResponseDto treinoAtualResponseDto = this.treinoService.buscarTreinoAtual(treinoConfigId);
        return ResponseEntity.ok(treinoAtualResponseDto);
    }

    @GetMapping("/treinos-por-configuracao/{treinoConfigId}")
    public ResponseEntity<TreinosResponseDto> visualizarTreinos(@PathVariable UUID treinoConfigId) {

        TreinosResponseDto treinosResponse = this.treinoService.visualizarTreinos(treinoConfigId);

        return ResponseEntity.ok(treinosResponse);
    }
}
