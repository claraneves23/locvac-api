package com.api.locvac.dto;

import jakarta.validation.constraints.NotNull;

public record EstoqueVacinaRequestDTO(
        @NotNull
        Long vacinaId,
        @NotNull
        Long unidadeId,
        @NotNull
        Integer quantidade
) {}

