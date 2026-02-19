package com.api.locvac.dto;

import com.api.locvac.validation.annotation.PeloMenosUmCampo;

import java.util.List;

@PeloMenosUmCampo
public record TipoVacinaPatchDTO(
        String nmVacina,
        String dsTipoVacina,
        List<Long> faixasEtariasIds,
        List<Long> restricoesIds,
        List<Long> cepasIds
) {}

