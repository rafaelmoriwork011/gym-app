package com.rvm.gym.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/execucao-treino")
@RequiredArgsConstructor
public class TreinoExecucaoController {

    /**
     * TODO: implementar
     * Regras: RF07
     */
    @PostMapping("/{id}")
    public ResponseEntity registroExecucao() {

        return ResponseEntity.ok()
                .build();
    }
}
