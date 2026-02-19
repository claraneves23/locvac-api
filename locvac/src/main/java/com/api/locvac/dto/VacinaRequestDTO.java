package com.api.locvac.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VacinaRequestDTO(
            @NotNull
            Long tipoVacinaId,

            @NotNull
            Long fabricanteId,

            @NotNull
            LocalDate dtFabricacao,

            @NotNull
            LocalDate dtValidade
)
{}


