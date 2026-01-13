package com.api.locvac.service.impl;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;
import com.api.locvac.mapper.VacinaMapper;
import com.api.locvac.model.associacao.TipoVacinaCepa;
import com.api.locvac.model.associacao.TipoVacinaFaixa;
import com.api.locvac.model.associacao.TipoVacinaRestricao;
import com.api.locvac.model.core.*;
import com.api.locvac.repository.*;
import com.api.locvac.service.VacinaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VacinaServiceImpl implements VacinaService {

    private final TipoVacinaFaixaRepository tipoVacinaFaixaRepository;
    private final TipoVacinaRestricaoRepository tipoVacinaRestricaoRepository;
    private final VacinaRepository vacinaRepository;
    private final FabricanteRepository fabricanteRepository;
    private final TipoVacinaRepository tipoVacinaRepository;
    private final FaixaEtariaRepository faixaEtariaRepository;
    private final RestricaoRepository restricaoRepository;
    private final TipoVacinaCepaRepository tipoVacinaCepaRepository;
    private final CepaRepository cepaRepository;
    private final VacinaMapper vacinaMapper;

    public VacinaServiceImpl(TipoVacinaFaixaRepository tipoVacinaFaixaRepository, TipoVacinaRestricaoRepository tipoVacinaRestricaoRepository, VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository, TipoVacinaRepository tipoVacinaRepository, FaixaEtariaRepository faixaEtariaRepository, RestricaoRepository restricaoRepository, TipoVacinaCepaRepository tipoVacinaCepaRepository, CepaRepository cepaRepository, VacinaMapper vacinaMapper) {
        this.tipoVacinaFaixaRepository = tipoVacinaFaixaRepository;
        this.tipoVacinaRestricaoRepository = tipoVacinaRestricaoRepository;
        this.vacinaRepository = vacinaRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.tipoVacinaRepository = tipoVacinaRepository;
        this.faixaEtariaRepository = faixaEtariaRepository;
        this.restricaoRepository = restricaoRepository;
        this.tipoVacinaCepaRepository = tipoVacinaCepaRepository;
        this.cepaRepository = cepaRepository;
        this.vacinaMapper = vacinaMapper;
    }


    @Override
    public void cadastrarVacina(VacinaRequestDTO dto) {

        Fabricante fabricante = buscarFabricante(dto.fabricanteId());
        TipoVacina tipoVacina = buscarTipoVacina(dto.tipoVacinaId());
        validarVacinaDuplicada(dto, fabricante, tipoVacina);
        List<FaixaEtaria> faixas = buscarFaixasEtarias(dto.faixasEtariasIds());
        List<Restricao> restricoes = buscarRestricao(dto.restricoesIds());
        List<Cepa> cepas = buscarCepa(dto.cepasIds());
        Vacina vacina = salvarVacina(dto, fabricante, tipoVacina);
        associarFaixasEtarias(tipoVacina, faixas);
        associarRestricoes(tipoVacina, restricoes);
        associarCepas(tipoVacina, cepas);
        vacinaRepository.save(vacina);

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

    private List<Cepa> buscarCepa(List<Long> ids) {

        List<Cepa> cepas = cepaRepository.findAllById(ids);

        if (cepas.size() != ids.size()) {
            throw new RuntimeException("Uma ou mais cepas não existem");
        }

        return cepas;
    }

    private void associarRestricoes(TipoVacina tipoVacina, List<Restricao> restricoes){

        for (Restricao restricao : restricoes) {
            tipoVacinaRestricaoRepository.save(
                    TipoVacinaRestricao.of(tipoVacina, restricao)
            );
        }
    }

    private void associarFaixasEtarias(TipoVacina tipoVacina, List<FaixaEtaria> faixas){

        for (FaixaEtaria faixa : faixas) {
            tipoVacinaFaixaRepository.save(
                    TipoVacinaFaixa.of(tipoVacina, faixa)
            );
        }
    }

    private void associarCepas(TipoVacina tipoVacina, List<Cepa> cepas){

        for (Cepa cepa : cepas) {
            tipoVacinaCepaRepository.save(
                    TipoVacinaCepa.of(tipoVacina, cepa)
            );
        }
    }

    @Override
    public List<VacinaResponseDTO> listarVacinas() {
        return vacinaRepository.findAll()
                .stream()
                .map(vacinaMapper::toResponse)
                .toList();
    }


}
