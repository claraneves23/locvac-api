package com.api.locvac.dto;

import java.time.LocalDate;
import java.util.List;

public record CampanhaRequestDTO(
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim,
        List<Long> unidadesIds,
        Long tipoVacinaId
) {}
