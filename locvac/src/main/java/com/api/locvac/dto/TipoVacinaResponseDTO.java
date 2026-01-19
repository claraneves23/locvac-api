package com.api.locvac.dto;

import java.util.List;

public record TipoVacinaResponseDTO(
        Long id,
        String nmVacina,
        String dsTipoVacina,
        List<String> faixasEtarias,
        List<String> restricoes,
        List<String> cepas
) {
}
