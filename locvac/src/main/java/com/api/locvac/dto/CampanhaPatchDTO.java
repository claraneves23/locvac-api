package com.api.locvac.dto;

import com.api.locvac.validation.annotation.PeloMenosUmCampo;

import java.time.LocalDate;
import java.util.List;

@PeloMenosUmCampo
public record CampanhaPatchDTO(
        String nome,
        LocalDate dataInicio,
        LocalDate dataFim,
        List<Long> unidadesIds,
        Long tipoVacinaId

) {
}
