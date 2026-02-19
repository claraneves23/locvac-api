package com.api.locvac.dto;


import com.api.locvac.validation.annotation.PeloMenosUmCampo;

import java.time.LocalDate;

@PeloMenosUmCampo
public record VacinaPatchDTO(
        Long tipoVacinaId,
        Long fabricanteId,
        LocalDate dtFabricacao,
        LocalDate dtValidade
) {
}
