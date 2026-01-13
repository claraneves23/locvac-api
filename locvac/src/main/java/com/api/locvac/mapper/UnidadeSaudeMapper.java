package com.api.locvac.mapper;

import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.dto.UnidadeSaudeResponseDTO;
import com.api.locvac.model.core.UnidadeSaude;
import org.springframework.stereotype.Component;

@Component
public class UnidadeSaudeMapper {

    public UnidadeSaude toEntity(UnidadeSaudeRequestDTO dto) {
        return new UnidadeSaude(
             dto.nmCep(),
                dto.dsEndereco(),
                dto.nmUnidade(),
                dto.longitude(),
                dto.latitude()
        );
    }

    public UnidadeSaudeResponseDTO toResponse(
            UnidadeSaude unidadeSaude
    ) {
        return new UnidadeSaudeResponseDTO(
                unidadeSaude.getCdUnidade(),
                unidadeSaude.getLatitude(),
                unidadeSaude.getLongitude(),
                unidadeSaude.getNmUnidade(),
                unidadeSaude.getNmCep(),
                unidadeSaude.getDsEndereco()
        );
    }
}
