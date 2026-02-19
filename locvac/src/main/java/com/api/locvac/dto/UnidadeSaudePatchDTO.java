package com.api.locvac.dto;

import com.api.locvac.validation.annotation.PeloMenosUmCampo;
import com.api.locvac.validation.annotation.TelefoneBR;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

@PeloMenosUmCampo
public record UnidadeSaudePatchDTO(
        String nmUnidade,

        @DecimalMin(value = "-90.0", inclusive = true)
        @DecimalMax(value = "90.0", inclusive = true)
        Double latitude,

        @DecimalMin(value = "-180.0", inclusive = true)
        @DecimalMax(value = "180.0", inclusive = true)
        Double longitude,

        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        String nmCep,

        String dsEndereco,

        @TelefoneBR
        String dsTelefone
) {
}
