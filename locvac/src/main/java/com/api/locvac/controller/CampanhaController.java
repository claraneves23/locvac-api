package com.api.locvac.controller;

import com.api.locvac.dto.CampanhaRequestDTO;
import com.api.locvac.dto.CampanhaResponseDTO;
import com.api.locvac.dto.TipoVacinaResponseDTO;
import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.mapper.CampanhaMapper;
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
    private final CampanhaMapper campanhaMapper;


    public CampanhaController(CampanhaService campanhaService, CampanhaMapper campanhaMapper) {
        this.campanhaService = campanhaService;
        this.campanhaMapper = campanhaMapper;
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

    @DeleteMapping("/deletaCampanha/{id}")
    public ResponseEntity<Void> deletaCampanha(@PathVariable Long id) {
        campanhaService.removerCampanha(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CampanhaResponseDTO> buscarPorId(@PathVariable Long id){
        Campanha campanha = campanhaService.buscarPorId(id);
        return ResponseEntity.ok(campanhaMapper.toResponse(campanha));
    }

    @GetMapping("/nome/{nmCampanha}")
    public ResponseEntity<CampanhaResponseDTO> filtrarPorNome(@PathVariable String nmCampanha){
        Campanha campanha = campanhaService.filtrarPorNome(nmCampanha);
        return ResponseEntity.ok(campanhaMapper.toResponse(campanha));
    }


}
