package com.api.locvac.controller;

import com.api.locvac.dto.EstoqueVacinaRequestDTO;
import com.api.locvac.dto.VacinaPorUnidadeResponseDTO;
import com.api.locvac.service.EstoqueVacinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoqueVacina")
public class EstoqueVacinaController {

    private final EstoqueVacinaService estoqueVacinaService;

    public EstoqueVacinaController(EstoqueVacinaService estoqueVacinaService) {
        this.estoqueVacinaService = estoqueVacinaService;
    }

    @PostMapping("/cadastrarEstoque")
    public ResponseEntity<Void> cadastrarEstoque(@RequestBody @Valid EstoqueVacinaRequestDTO dto) {
        estoqueVacinaService.cadastrarEstoque(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{unidadeId}/quantidade")
    public List<VacinaPorUnidadeResponseDTO> listarEstoqueVacina(
            @PathVariable Long unidadeId) {

        return estoqueVacinaService.listarVacinasPorUnidade(unidadeId);
    }

}
