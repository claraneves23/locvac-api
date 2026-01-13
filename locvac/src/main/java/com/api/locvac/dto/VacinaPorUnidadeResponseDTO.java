package com.api.locvac.dto;

import com.api.locvac.model.core.Cepa;

import java.time.LocalDate;
import java.util.List;

//lembrar de ver as anotacoes
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

// terminar estoque

