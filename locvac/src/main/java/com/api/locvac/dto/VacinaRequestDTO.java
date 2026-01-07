package com.api.locvac.dto;

import java.time.LocalDate;
import java.util.List;

//quantidade e arrumar o mapper
public record VacinaRequestDTO(
            Long tipoVacinaId,
            Long fabricanteId,
            LocalDate dtFabricacao,
            LocalDate dtValidade,
            String dsVacina,
            List<Long> faixasEtariasIds,
            List<Long> restricoesIds) {}


