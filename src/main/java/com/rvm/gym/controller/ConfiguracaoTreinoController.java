package com.rvm.gym.controller;

import com.rvm.gym.dto.request.ConfiguracaoTreinoRequestDto;
import com.rvm.gym.dto.response.ValidacaoTreinoRenovacaoResponseDto;
import com.rvm.gym.service.TreinoConfiguracaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/configurar-treino")
@RequiredArgsConstructor
public class ConfiguracaoTreinoController {

    private final TreinoConfiguracaoService configuracaoTreinoService;

    @PostMapping("/configurar")
    public ResponseEntity configurar(@RequestBody @Valid ConfiguracaoTreinoRequestDto configuracaoTreinoRequestDto) {

        configuracaoTreinoService.configurar(configuracaoTreinoRequestDto);

        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/reconfigurar/{id}")
    public ResponseEntity reconfigurar(@PathVariable UUID id) {

        configuracaoTreinoService.reconfigurarTreinoDeUsuario(id);

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/verificar-renovacao/{id}")
    public ResponseEntity<ValidacaoTreinoRenovacaoResponseDto> verificarRenovacao(@PathVariable UUID id) {

        ValidacaoTreinoRenovacaoResponseDto validacaoTreinoRenovacaoResponseDto = this.configuracaoTreinoService.verificarSeTreinoDeveRenovar(id);

        return ResponseEntity.ok(validacaoTreinoRenovacaoResponseDto);
    }

}
