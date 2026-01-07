package com.api.locvac.service.impl;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.mapper.VacinaMapper;
import com.api.locvac.model.associacao.FaixaVacina;
import com.api.locvac.model.associacao.VacinaRestricao;
import com.api.locvac.model.core.*;
import com.api.locvac.repository.*;
import com.api.locvac.service.VacinaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VacinaServiceImpl implements VacinaService {

    private final VacinaRepository vacinaRepository;
    private final FabricanteRepository fabricanteRepository;
    private final TipoVacinaRepository tipoVacinaRepository;
    private final FaixaEtariaRepository faixaEtariaRepository;
    private final FaixaVacinaRepository faixaVacinaRepository;
    private final RestricaoRepository restricaoRepository;
    private final VacinaRestricaoRepository vacinaRestricaoRepository;
    private final VacinaMapper vacinaMapper;

    public VacinaServiceImpl(VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository, TipoVacinaRepository tipoVacinaRepository, FaixaEtariaRepository faixaEtariaRepository, FaixaVacinaRepository faixaVacinaRepository, RestricaoRepository restricaoRepository, VacinaRestricaoRepository vacinaRestricaoRepository, VacinaMapper vacinaMapper) {
        this.vacinaRepository = vacinaRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.tipoVacinaRepository = tipoVacinaRepository;
        this.faixaEtariaRepository = faixaEtariaRepository;
        this.faixaVacinaRepository = faixaVacinaRepository;
        this.restricaoRepository = restricaoRepository;
        this.vacinaRestricaoRepository = vacinaRestricaoRepository;
        this.vacinaMapper = vacinaMapper;
    }


    @Override
    public void cadastrarVacina(VacinaRequestDTO dto) {

        Fabricante fabricante = buscarFabricante(dto.fabricanteId());
        TipoVacina tipoVacina = buscarTipoVacina(dto.tipoVacinaId());
        validarVacinaDuplicada(dto, fabricante, tipoVacina);
        List<FaixaEtaria> faixas = buscarFaixasEtarias(dto.faixasEtariasIds());
        List<Restricao> restricoes = buscarRestricao(dto.restricoesIds());
        Vacina vacina = salvarVacina(dto, fabricante, tipoVacina);
        associarFaixasEtarias(vacina, faixas);
        associarRestricoes(vacina, restricoes);

    }

    private Fabricante buscarFabricante(Long fabricanteId) {
        return fabricanteRepository.findById(fabricanteId)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
    }

    private TipoVacina buscarTipoVacina(Long tipoVacinaId) {
        return tipoVacinaRepository.findById(tipoVacinaId)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
    }


    private void validarVacinaDuplicada(VacinaRequestDTO dto, Fabricante fabricante, TipoVacina tipoVacina) {
        boolean existe = vacinaRepository
                .existsByTipoVacinaAndFabricanteAndDtFabricacao(
                        tipoVacina,
                        fabricante,
                        dto.dtFabricacao()
                );

        if (existe) {
            throw new RuntimeException("Vacina já cadastrada");
        }
    }

    private Vacina salvarVacina(VacinaRequestDTO dto, Fabricante fabricante, TipoVacina tipoVacina) {
        Vacina vacina = vacinaMapper.toEntity(dto, fabricante, tipoVacina);
        return vacinaRepository.save(vacina);
    }

    private List<FaixaEtaria> buscarFaixasEtarias(List<Long> ids) {

        List<FaixaEtaria> faixas = faixaEtariaRepository.findAllById(ids);

        if (faixas.size() != ids.size()) {
            throw new RuntimeException("Uma ou mais faixas etárias não existem");
        }

        return faixas;
    }

    private List<Restricao> buscarRestricao(List<Long> ids) {

        List<Restricao> restricoes = restricaoRepository.findAllById(ids);

        if (restricoes.size() != ids.size()) {
            throw new RuntimeException("Uma ou mais restricoes não existem");
        }

        return restricoes;
    }

    private void associarRestricoes(Vacina vacina, List<Restricao> restricoes){

        for(Restricao restricao : restricoes){
            vacinaRestricaoRepository.save(VacinaRestricao.of(vacina, restricao));
        }
    }

    private void associarFaixasEtarias(Vacina vacina, List<FaixaEtaria> faixas){

        for(FaixaEtaria faixa : faixas){
            faixaVacinaRepository.save(FaixaVacina.of(vacina, faixa));
        }
    }

    @Override
    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();

    }


}
