package com.api.locvac.service.impl;

import com.api.locvac.dto.VacinaPatchDTO;
import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;
import com.api.locvac.mapper.VacinaMapper;
import com.api.locvac.mapper.patch.UnidadeSaudePatchMapper;
import com.api.locvac.mapper.patch.VacinaPatchMapper;
import com.api.locvac.model.core.*;
import com.api.locvac.repository.*;
import com.api.locvac.service.VacinaService;
import com.api.locvac.utils.ValidacaoPeriodoUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class VacinaServiceImpl implements VacinaService {


    private final VacinaRepository vacinaRepository;
    private final FabricanteRepository fabricanteRepository;
    private final TipoVacinaRepository tipoVacinaRepository;
    private final VacinaMapper vacinaMapper;
    private final VacinaPatchMapper mapper;

    public VacinaServiceImpl(VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository, TipoVacinaRepository tipoVacinaRepository, VacinaMapper vacinaMapper, VacinaPatchMapper mapper) {
        this.vacinaRepository = vacinaRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.tipoVacinaRepository = tipoVacinaRepository;
        this.vacinaMapper = vacinaMapper;
        this.mapper = mapper;
    }

    @Override
    public void cadastrarVacina(VacinaRequestDTO dto) {

        Fabricante fabricante = buscarFabricante(dto.fabricanteId());
        TipoVacina tipoVacina = buscarTipoVacina(dto.tipoVacinaId());
        ValidacaoPeriodoUtils.validarDataFinalPosterior(dto.dtFabricacao(), dto.dtValidade());
        validarVacinaDuplicada(dto.dtFabricacao(), fabricante, tipoVacina, null);
        Vacina vacina = salvarVacina(dto, fabricante, tipoVacina);
        vacinaRepository.save(vacina);

    }

    @Override
    public void removerVacina(Long vacinaId){

        if(!vacinaRepository.existsById(vacinaId)){
            throw new RuntimeException("Vacina não existe");
        }
        vacinaRepository.deleteById(vacinaId);
    }

    private Fabricante buscarFabricante(Long fabricanteId) {
        return fabricanteRepository.findById(fabricanteId)
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));
    }

    private TipoVacina buscarTipoVacina(Long tipoVacinaId) {
        return tipoVacinaRepository.findById(tipoVacinaId)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
    }

    private void validarVacinaDuplicada(LocalDate dtFabricacao, Fabricante fabricante, TipoVacina tipoVacina, Long vacinaId) {

        boolean existe = vacinaRepository
                .existsByTipoVacinaAndFabricanteAndDtFabricacaoAndIdNot(
                        tipoVacina,
                        fabricante,
                        dtFabricacao,
                        vacinaId
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

    @Override
    public void atualizarVacina(Long vacinaId, VacinaPatchDTO vacinaAtualizada) {
        Vacina vacina =  vacinaRepository.findById(vacinaId)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada"));

        if (vacinaAtualizada.fabricanteId() != null) {
            Fabricante fabricante = buscarFabricante(vacinaAtualizada.fabricanteId());
            vacina.setFabricante(fabricante);
        }

        if (vacinaAtualizada.tipoVacinaId() != null) {
            TipoVacina tipoVacina = buscarTipoVacina(vacinaAtualizada.tipoVacinaId());
            vacina.setTipoVacina(tipoVacina);
        }

        mapper.patch(vacinaAtualizada, vacina);

        ValidacaoPeriodoUtils.validarDataFinalPosterior(vacina.getDtFabricacao(), vacina.getDtValidade());

        validarVacinaDuplicada(vacina.getDtFabricacao(), vacina.getFabricante(), vacina.getTipoVacina(), vacinaId);

        vacinaRepository.save(vacina);
    }


}
