package com.api.locvac.dto;

import com.api.locvac.validation.annotation.TelefoneBR;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UnidadeSaudeRequestDTO(

        @NotNull
        @DecimalMin(value = "-90.0", inclusive = true)
        @DecimalMax(value = "90.0", inclusive = true)
        Double latitude,

        @NotNull
        @DecimalMin(value = "-180.0", inclusive = true)
        @DecimalMax(value = "180.0", inclusive = true)
        Double longitude,

        @NotNull
        String nmUnidade,

        @NotNull
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        String nmCep,

        @NotNull
        String dsEndereco,

        @NotNull
        @TelefoneBR
        String dsTelefone
) {
}
