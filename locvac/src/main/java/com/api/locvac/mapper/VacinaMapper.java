package com.api.locvac.mapper;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;
import com.api.locvac.model.core.Fabricante;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.core.Vacina;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacinaMapper {

    public Vacina toEntity(VacinaRequestDTO dto, Fabricante fabricante, TipoVacina tipoVacina) {
        return new Vacina(
                fabricante,
                tipoVacina,
                dto.dtFabricacao(),
                dto.dtValidade(),
                dto.dsVacina()
        );
    }

    public VacinaResponseDTO toResponse(
            Vacina vacina,
            Integer quantidade,
            List<String> faixasEtarias,
            List<String> restricoes
    ) {
        return new VacinaResponseDTO(
                vacina.getId(),
                vacina.getTipoVacina().getNmVacina(),
                vacina.getDtFabricacao(),
                vacina.getDtValidade(),
                vacina.getFabricante().getNmFabricante(),
                quantidade,
                faixasEtarias,
                restricoes
        );
    }

}

