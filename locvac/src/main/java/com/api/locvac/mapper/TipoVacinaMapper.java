package com.api.locvac.mapper;

import com.api.locvac.dto.TipoVacinaResponseDTO;
import com.api.locvac.model.core.TipoVacina;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoVacinaMapper {

    public TipoVacinaResponseDTO toResponse(TipoVacina tipoVacina) {
        return new TipoVacinaResponseDTO(
                tipoVacina.getCdTipoVacina(),
                tipoVacina.getNmVacina(),
                tipoVacina.getDsTipoVacina(),
                getFaixas(tipoVacina),
                getRestricoes(tipoVacina),
                getCepas(tipoVacina)
        );
    }

    private static List<String> getFaixas(TipoVacina tipo) {
        return tipo.getFaixasEtarias()
                .stream()
                .map(tf -> tf.getFaixa().getDescricao())
                .toList();
    }

    private static List<String> getRestricoes(TipoVacina tipo) {
        return tipo.getRestricoes()
                .stream()
                .map(tr -> tr.getRestricao().getDsRestricao())
                .toList();
    }

    private static List<String> getCepas(TipoVacina tipo) {
        return tipo.getCepas()
                .stream()
                .map(tc -> tc.getCepa().getNome())
                .toList();
    }
}
