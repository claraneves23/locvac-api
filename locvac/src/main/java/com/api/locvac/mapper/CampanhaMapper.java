package com.api.locvac.mapper;

import com.api.locvac.dto.*;
import com.api.locvac.model.associacao.CampanhaUnidade;
import com.api.locvac.model.associacao.EstoqueVacina;
import com.api.locvac.model.core.Campanha;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.model.core.Vacina;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampanhaMapper {

    public Campanha toEntity(
            CampanhaRequestDTO dto,
            TipoVacina tipoVacina,
            List<UnidadeSaude> unidades
    ) {
        Campanha campanha = new Campanha(
                dto.nome(),
                dto.dataInicio(),
                dto.dataFim(),
                tipoVacina
        );

        unidades.forEach(unidade ->
                campanha.getUnidades()
                        .add(CampanhaUnidade.of(campanha, unidade))
        );

        return campanha;
    }

    public CampanhaResponseDTO toResponse(Campanha campanha) {

        String tipoVacina = campanha.getTipoVacina().getNmVacina();

        List<String> unidades = campanha.getUnidades()
                .stream()
                .map(cu -> cu.getUnidadeSaude().getNmUnidade())
                .toList();

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
