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


    private final VacinaRepository vacinaRepository;
    private final FabricanteRepository fabricanteRepository;
    private final TipoVacinaRepository tipoVacinaRepository;
    private final VacinaMapper vacinaMapper;

    public VacinaServiceImpl(VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository, TipoVacinaRepository tipoVacinaRepository, VacinaMapper vacinaMapper) {
        this.vacinaRepository = vacinaRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.tipoVacinaRepository = tipoVacinaRepository;
        this.vacinaMapper = vacinaMapper;
    }

    @Override
    public void cadastrarVacina(VacinaRequestDTO dto) {

        Fabricante fabricante = buscarFabricante(dto.fabricanteId());
        TipoVacina tipoVacina = buscarTipoVacina(dto.tipoVacinaId());
        validarVacinaDuplicada(dto, fabricante, tipoVacina);
        Vacina vacina = salvarVacina(dto, fabricante, tipoVacina);
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

    @Override
    public List<VacinaResponseDTO> listarVacinas() {
        return vacinaRepository.findAll()
                .stream()
                .map(vacinaMapper::toResponse)
                .toList();
    }


}
