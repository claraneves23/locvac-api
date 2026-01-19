package com.api.locvac.dto;

import com.api.locvac.model.id.EstoqueVacinaId;

import java.time.LocalDate;
import java.util.List;

//quantidade e arrumar o mapper
public record VacinaRequestDTO(
            Long tipoVacinaId,
            Long fabricanteId,
            LocalDate dtFabricacao,
            LocalDate dtValidade)
{}


