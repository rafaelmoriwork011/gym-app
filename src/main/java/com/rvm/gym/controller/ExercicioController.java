package com.rvm.gym.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
public class ExercicioController {

    /**
     * TODO: implementar
     */
    @GetMapping
    public ResponseEntity<Void> getAll() {
        return ResponseEntity.noContent()
                .build();
    }

    /**
     * TODO: implementar
     */
    @GetMapping("/{id}")
    public ResponseEntity<Void> getById(@PathVariable Long id) {
        return ResponseEntity.noContent()
                .build();
    }

    /**
     * TODO: implementar
     */
    @PostMapping
    public ResponseEntity<Void> create() {
        return ResponseEntity.noContent()
                .build();
    }

    /**
     * TODO: implementar
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        return ResponseEntity.noContent()
                .build();
    }

    /**
     * TODO: implementar
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent()
                .build();
    }

}
