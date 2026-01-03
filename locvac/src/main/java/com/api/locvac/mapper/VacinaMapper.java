package com.api.locvac.mapper;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;
import com.api.locvac.model.core.Fabricante;
import com.api.locvac.model.core.Vacina;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacinaMapper {

    public Vacina toEntity(VacinaRequestDTO dto, Fabricante fabricante) {
        return new Vacina(
                fabricante,
                dto.getNome(),
                dto.getDtFabricacao(),
                dto.getDtValidade(),
                dto.getDescricao()
        );
    }

    public VacinaResponseDTO toResponse(
            Vacina vacina,
            Integer quantidade,
            List<String> faixasEtarias
    ) {
        return new VacinaResponseDTO(
                vacina.getId(),
                vacina.getNmVacina(),
                vacina.getDtFabricacao(),
                vacina.getDtValidade(),
                vacina.getFabricante().getNmFabricante(),
                quantidade,
                faixasEtarias
        );
    }

}

