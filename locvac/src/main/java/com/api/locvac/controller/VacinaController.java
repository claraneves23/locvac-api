package com.api.locvac.controller;

import com.api.locvac.dto.UnidadeSaudePatchDTO;
import com.api.locvac.dto.VacinaPatchDTO;
import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;
import com.api.locvac.service.VacinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {

    private final VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @PostMapping("/novaVacina")
    public ResponseEntity<Void> cadastrarVacina(@RequestBody @Valid VacinaRequestDTO dto) {
        vacinaService.cadastrarVacina(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<VacinaResponseDTO> listarVacinas() {
        return vacinaService.listarVacinas();
    }

    @DeleteMapping("/deletaVacina/{id}")
    public ResponseEntity<Void> deletaVacina(@PathVariable Long id) {
        vacinaService.removerVacina(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/atualizaVacina/{id}")
    public ResponseEntity<Void> atualizaVacina(@PathVariable Long id, @RequestBody @Valid VacinaPatchDTO dto) {
        vacinaService.atualizarVacina(id,dto);
        return ResponseEntity.noContent().build();
    }

}
