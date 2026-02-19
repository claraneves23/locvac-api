package com.api.locvac.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CampanhaRequestDTO(
        @NotNull
        String nome,

        @NotNull
        LocalDate dataInicio,

        @NotNull
        LocalDate dataFim,

        @NotNull
        List<Long> unidadesIds,

        @NotNull
        Long tipoVacinaId
) {}
