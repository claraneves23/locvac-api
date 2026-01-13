package com.api.locvac.controller;

import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.service.UnidadeSaudeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidadesSaude")
public class UnidadeSaudeController{

    private final UnidadeSaudeService unidadeSaudeService;

    public UnidadeSaudeController(UnidadeSaudeService unidadeSaudeService) {
        this.unidadeSaudeService = unidadeSaudeService;
    }

    @PostMapping("/novaUnidade")
    public ResponseEntity<Void> cadastrarUnidade(@RequestBody @Valid UnidadeSaudeRequestDTO dto) {
        unidadeSaudeService.cadastrarUnidade(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<UnidadeSaude> listarUnidades() {
        return unidadeSaudeService.listarUnidade();
    }
}
