package com.api.locvac.mapper;

import com.api.locvac.dto.VacinaPorUnidadeResponseDTO;
import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;
import com.api.locvac.model.associacao.EstoqueVacina;
import com.api.locvac.model.core.Fabricante;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.core.Vacina;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacinaMapper {

    public Vacina toEntity(VacinaRequestDTO dto, Fabricante fabricante, TipoVacina tipoVacina) {
        return new Vacina(
                fabricante,
                tipoVacina,
                dto.dtFabricacao(),
                dto.dtValidade()
        );
    }

    public VacinaResponseDTO toResponse(Vacina vacina) {
        TipoVacina tipo = vacina.getTipoVacina();

        return new VacinaResponseDTO(
                vacina.getId(),
                tipo.getNmVacina(),
                vacina.getDtFabricacao(),
                vacina.getDtValidade(),
                vacina.getFabricante().getNmFabricante()
        );
    }

    public VacinaPorUnidadeResponseDTO toPorUnidade(EstoqueVacina estoque) {

        Vacina vacina = estoque.getVacina();
        TipoVacina tipo = vacina.getTipoVacina();

        return new VacinaPorUnidadeResponseDTO(
                vacina.getId(),
                tipo.getNmVacina(),
                vacina.getDtFabricacao(),
                vacina.getDtValidade(),
                estoque.getQuantidade(),
                getFaixas(tipo),
                getRestricoes(tipo),
                getCepas(tipo)
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

