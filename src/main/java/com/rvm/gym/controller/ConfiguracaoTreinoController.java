package com.rvm.gym.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configurar-treino")
@RequiredArgsConstructor
public class ConfiguracaoTreinoController {

    /**
     * TODO: implementar
     * Regras: RF02, RF03
     */
    @PostMapping("/configurar")
    public ResponseEntity configurar() {

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
