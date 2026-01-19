package com.api.locvac.dto;

import java.time.LocalDate;
import java.util.List;

public record VacinaPorUnidadeResponseDTO(
        Long vacinaId,
        String nmVacina,
        LocalDate dtFabricacao,
        LocalDate dtValidade,
        Integer quantidade,
        List<String> faixaEtaria,
        List<String> restricoes,
        List<String> cepas
) {}


