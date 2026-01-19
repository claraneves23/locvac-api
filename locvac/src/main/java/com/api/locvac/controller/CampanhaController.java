package com.api.locvac.controller;

import com.api.locvac.dto.CampanhaRequestDTO;
import com.api.locvac.dto.CampanhaResponseDTO;
import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.model.core.Campanha;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.service.CampanhaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {

    private final CampanhaService campanhaService;

    public CampanhaController(CampanhaService campanhaService) {
        this.campanhaService = campanhaService;
    }

    @PostMapping("/novaCampanha")
    public ResponseEntity<Void> cadastrarCampanha(@RequestBody @Valid CampanhaRequestDTO dto) {
        campanhaService.cadastrarCampanha(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<CampanhaResponseDTO> listarUnidades() {
        return campanhaService.listarCampanhas();
    }
}
