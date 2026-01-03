package com.api.locvac.controller;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.service.VacinaService;
import com.api.locvac.service.impl.VacinaServiceImpl;
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
    public List<Vacina> listarVacinas() {
        return vacinaService.listarVacinas();
    }




}
