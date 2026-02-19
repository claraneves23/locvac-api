package com.api.locvac.dto;

public record UnidadeSaudeResponseDTO(
        Long cdUnidade,
        Double latitude,
        Double longitude,
        String nmUnidade,
        String nmCep,
        String dsEndereco,
        String dsTelefone

) {
}

