package com.api.locvac.service.impl;

import com.api.locvac.dto.TipoVacinaRequestDTO;
import com.api.locvac.dto.TipoVacinaResponseDTO;
import com.api.locvac.mapper.TipoVacinaMapper;
import com.api.locvac.model.associacao.TipoVacinaCepa;
import com.api.locvac.model.associacao.TipoVacinaFaixa;
import com.api.locvac.model.associacao.TipoVacinaRestricao;
import com.api.locvac.model.core.Cepa;
import com.api.locvac.model.core.FaixaEtaria;
import com.api.locvac.model.core.Restricao;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.repository.*;
import com.api.locvac.service.TipoVacinaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TipoVacinaServiceImpl implements TipoVacinaService {

    private final TipoVacinaRepository tipoVacinaRepository;
    private final FaixaEtariaRepository faixaEtariaRepository;
    private final RestricaoRepository restricaoRepository;
    private final CepaRepository cepaRepository;
    private final TipoVacinaFaixaRepository tipoVacinaFaixaRepository;
    private final TipoVacinaRestricaoRepository tipoVacinaRestricaoRepository;
    private final TipoVacinaCepaRepository tipoVacinaCepaRepository;
    private final TipoVacinaMapper tipoVacinaMapper;

    public TipoVacinaServiceImpl(
            TipoVacinaRepository tipoVacinaRepository,
            FaixaEtariaRepository faixaEtariaRepository,
            RestricaoRepository restricaoRepository,
            CepaRepository cepaRepository,
            TipoVacinaFaixaRepository tipoVacinaFaixaRepository,
            TipoVacinaRestricaoRepository tipoVacinaRestricaoRepository,
            TipoVacinaCepaRepository tipoVacinaCepaRepository, TipoVacinaMapper tipoVacinaMapper
    ) {
        this.tipoVacinaRepository = tipoVacinaRepository;
        this.faixaEtariaRepository = faixaEtariaRepository;
        this.restricaoRepository = restricaoRepository;
        this.cepaRepository = cepaRepository;
        this.tipoVacinaFaixaRepository = tipoVacinaFaixaRepository;
        this.tipoVacinaRestricaoRepository = tipoVacinaRestricaoRepository;
        this.tipoVacinaCepaRepository = tipoVacinaCepaRepository;
        this.tipoVacinaMapper = tipoVacinaMapper;
    }

    @Override
    public void cadastrarTipoVacina(TipoVacinaRequestDTO dto) {

        TipoVacina tipoVacina = new TipoVacina(
                dto.nmVacina(),
                dto.dsTipoVacina()
        );

        tipoVacinaRepository.save(tipoVacina);

        associarFaixasEtarias(tipoVacina, dto.faixasEtariasIds());
        associarRestricoes(tipoVacina, dto.restricoesIds());
        associarCepas(tipoVacina, dto.cepasIds());
    }

    private void associarFaixasEtarias(TipoVacina tipoVacina, List<Long> ids) {

        List<FaixaEtaria> faixas = faixaEtariaRepository.findAllById(ids);

        if (faixas.size() != ids.size()) {
            throw new RuntimeException("Faixa etária inválida");
        }

        faixas.forEach(faixa ->
                tipoVacinaFaixaRepository.save(
                        TipoVacinaFaixa.of(tipoVacina, faixa)
                )
        );
    }

    private void associarRestricoes(TipoVacina tipoVacina, List<Long> ids) {

        List<Restricao> restricoes = restricaoRepository.findAllById(ids);

        if (restricoes.size() != ids.size()) {
            throw new RuntimeException("Restrição inválida");
        }

        restricoes.forEach(restricao ->
                tipoVacinaRestricaoRepository.save(
                        TipoVacinaRestricao.of(tipoVacina, restricao)
                )
        );
    }

    private void associarCepas(TipoVacina tipoVacina, List<Long> ids) {

        List<Cepa> cepas = cepaRepository.findAllById(ids);

        if (cepas.size() != ids.size()) {
            throw new RuntimeException("Cepa inválida");
        }

        cepas.forEach(cepa ->
                tipoVacinaCepaRepository.save(
                        TipoVacinaCepa.of(tipoVacina, cepa)
                )
        );
    }

    @Override
    public List<TipoVacinaResponseDTO> listarTiposVacina() {
        return tipoVacinaRepository.findAll()
                .stream()
                .map(tipoVacinaMapper::toResponse)
                .toList();
    }

    @Override
    public TipoVacinaResponseDTO filtrarPorNome(String nmVacina) {
        TipoVacina tipoVacina = tipoVacinaRepository.findByNmVacinaContainingIgnoreCase(nmVacina)
                .orElseThrow(() -> new RuntimeException("Tipo de vacina não encontrado"));

        return tipoVacinaMapper.toResponse(tipoVacina);
    }

    @Override
    public TipoVacinaResponseDTO buscarPorId(Long id) {
        TipoVacina tipoVacina = tipoVacinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de vacina não encontrado"));

        return tipoVacinaMapper.toResponse(tipoVacina);
    }

    @Override
    public void removerTipoVacina(Long tipoVacinaId){
        if(!tipoVacinaRepository.existsById(tipoVacinaId)){
            throw new RuntimeException("Vacina não existe");
        }

        tipoVacinaRepository.deleteById(tipoVacinaId);
    }


}
