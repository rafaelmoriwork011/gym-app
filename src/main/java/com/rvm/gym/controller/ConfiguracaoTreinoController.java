package com.rvm.gym.controller;

import com.rvm.gym.dto.request.ConfiguracaoTreinoRequestDto;
import com.rvm.gym.service.TreinoConfiguracaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * TODO: implementar
     * Regras: RF05, RF06
     */
    @PostMapping("/reconfigurar")
    public ResponseEntity reconfigurar() {

        return ResponseEntity.ok()
                .build();
    }

    /**
     * TODO: implementar
     * Regras: RF06
     * Responsável por verificar se o treino deve ser renovado
     */
    @GetMapping("/{id}")
    public ResponseEntity verificarRenovacao() {

        return ResponseEntity.ok()
                .build();
    }

}
