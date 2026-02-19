package com.api.locvac.dto;

import com.api.locvac.validation.annotation.PeloMenosUmCampo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@PeloMenosUmCampo
public record EstoqueVacinaPatchDTO(
        @NotNull
        @Min(0)
        Integer quantidade
) {}


