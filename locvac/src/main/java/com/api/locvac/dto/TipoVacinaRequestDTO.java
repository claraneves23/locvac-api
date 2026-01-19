package com.api.locvac.dto;

import java.util.List;

public record TipoVacinaRequestDTO(
        String nmVacina,
        String dsTipoVacina,
        List<Long> faixasEtariasIds,
        List<Long> restricoesIds,
        List<Long> cepasIds
) {}

