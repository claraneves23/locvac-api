package com.api.locvac.dto;

public record UnidadeSaudeRequestDTO(
        Double latitude,
        Double longitude,
        String nmUnidade,
        String nmCep,
        String dsEndereco
) {
}
