package com.api.locvac.dto;

import java.time.LocalDate;
import java.util.List;

public record CampanhaResponseDTO(
        Long campanhaId,
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim,
        List<String> unidades,
        String tipoVacina
) {
}
