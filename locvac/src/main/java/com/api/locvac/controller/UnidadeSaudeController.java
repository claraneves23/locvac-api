package com.api.locvac.controller;

import com.api.locvac.dto.UnidadeSaudePatchDTO;
import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.dto.UnidadeSaudeResponseDTO;
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
    public List<UnidadeSaudeResponseDTO> listarUnidades() {
        return unidadeSaudeService.listarUnidade();
    }

    @DeleteMapping("/deletaUnidade/{id}")
    public ResponseEntity<Void> deletaUnidade(@PathVariable Long id) {
        unidadeSaudeService.removerUnidadeSaude(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UnidadeSaudeResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(unidadeSaudeService.buscarPorId(id));
    }

    @GetMapping("/nome/{nomeUnidade}")
    public ResponseEntity<UnidadeSaudeResponseDTO> filtrarPorNome(@PathVariable String nomeVacina){
        return ResponseEntity.ok(unidadeSaudeService.filtrarPorNome(nomeVacina));
    }

    @PatchMapping("/atualizaUnidade/{id}")
    public ResponseEntity<Void> atualizaUnidade(@PathVariable Long id, @RequestBody @Valid UnidadeSaudePatchDTO dto) {
        unidadeSaudeService.atualizarUnidade(id,dto);
        return ResponseEntity.noContent().build();
    }

}
