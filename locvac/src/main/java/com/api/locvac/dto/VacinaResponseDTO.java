package com.api.locvac.dto;

import com.api.locvac.model.core.Fabricante;

import java.time.LocalDate;
import java.util.List;

public record VacinaResponseDTO(
        Long id,
        String nmVacina,
        LocalDate dtFabricacao,
        LocalDate dtValidade,
        String nmFabricante,
        Integer quantidade,
        List<String> faixaEtaria
) {
}
