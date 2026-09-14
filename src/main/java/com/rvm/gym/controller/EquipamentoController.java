package com.rvm.gym.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    /**
     * TODO: implementar
     * Regras: RF01
     */
    @GetMapping
    public ResponseEntity<Void> getAll() {
        return ResponseEntity.noContent()
                .build();
    }

    /**
     * TODO: implementar
     * Regras: RF01
     */
    @GetMapping("/{id}")
    public ResponseEntity<Void> getById(@PathVariable Long id) {
        return ResponseEntity.noContent()
                .build();
    }

    /**
     * TODO: implementar
     * Regras: RF01
     */
    @PostMapping
    public ResponseEntity<Void> create() {
        return ResponseEntity.noContent()
                .build();
    }

    /**
     * TODO: implementar
     * Regras: RF01
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        return ResponseEntity.noContent()
                .build();
    }

    /**
     * TODO: implementar
     * Regras: RF01
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent()
                .build();
    }
}
