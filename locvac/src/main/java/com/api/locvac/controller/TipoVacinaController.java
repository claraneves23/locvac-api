package com.api.locvac.controller;

import com.api.locvac.dto.TipoVacinaRequestDTO;
import com.api.locvac.dto.TipoVacinaResponseDTO;
import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.service.TipoVacinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoVacinas")
public class TipoVacinaController {

    private final TipoVacinaService tipoVacinaService;

    public TipoVacinaController(TipoVacinaService tipoVacinaService) {
        this.tipoVacinaService = tipoVacinaService;
    }

    @PostMapping("/novoTipoVacina")
    public ResponseEntity<Void> cadastrarTipoVacina(@RequestBody @Valid TipoVacinaRequestDTO dto) {
        tipoVacinaService.cadastrarTipoVacina(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<TipoVacinaResponseDTO> listarTipoVacinas() {
        return tipoVacinaService.listarTiposVacina();
    }

    @DeleteMapping("/deletaTipoVacina/{id}")
    public ResponseEntity<Void> deletaTipoVacina(@PathVariable Long id) {
        tipoVacinaService.removerTipoVacina(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TipoVacinaResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(tipoVacinaService.buscarPorId(id));
    }

    @GetMapping("/nome/{nomeVacina}")
    public ResponseEntity<TipoVacinaResponseDTO> filtrarPorNome(@PathVariable String nomeVacina){
        return ResponseEntity.ok(tipoVacinaService.filtrarPorNome(nomeVacina));
    }
}
