package com.api.locvac.mapper;

import com.api.locvac.dto.*;
import com.api.locvac.model.associacao.EstoqueVacina;
import com.api.locvac.model.core.Campanha;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.model.core.Vacina;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampanhaMapper {

    public Campanha toEntity(CampanhaRequestDTO dto, TipoVacina tipoVacina){
        return new Campanha(
                dto.nome(),
                dto.dataInicio(),
                dto.dataFim(),
                tipoVacina
        );
    }


    public CampanhaResponseDTO toResponse(
            Campanha campanha,
            String tipoVacina,
            List<String> unidades
    ) {
        return new CampanhaResponseDTO(
                campanha.getCdCampanha(),
                campanha.getNmCampanha(),
                campanha.getDtComecoCampanha(),
                campanha.getDtFimCampanha(),
                unidades,
                tipoVacina
        );
    }



}
