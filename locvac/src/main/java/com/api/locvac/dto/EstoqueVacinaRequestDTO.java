package com.api.locvac.dto;

public record EstoqueVacinaRequestDTO(
        Long vacinaId,
        Long unidadeId,
        Integer quantidade
) {}

