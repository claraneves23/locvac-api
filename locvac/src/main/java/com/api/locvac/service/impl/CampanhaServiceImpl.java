package com.api.locvac.service.impl;

import com.api.locvac.dto.CampanhaRequestDTO;
import com.api.locvac.dto.CampanhaResponseDTO;
import com.api.locvac.mapper.CampanhaMapper;
import com.api.locvac.model.associacao.CampanhaUnidade;
import com.api.locvac.model.core.*;
import com.api.locvac.repository.*;
import com.api.locvac.service.CampanhaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CampanhaServiceImpl implements CampanhaService {

    private final CampanhaMapper campanhaMapper;
    private final CampanhaRepository campanhaRepository;
    private final CampanhaUnidadeRepository campanhaUnidadeRepository;
    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final TipoVacinaRepository tipoVacinaRepository;

    public CampanhaServiceImpl(CampanhaMapper campanhaMapper, CampanhaRepository campanhaRepository, CampanhaUnidadeRepository campanhaUnidadeRepository, UnidadeSaudeRepository unidadeSaudeRepository, TipoVacinaRepository tipoVacinaRepository) {
        this.campanhaMapper = campanhaMapper;
        this.campanhaRepository = campanhaRepository;
        this.campanhaUnidadeRepository = campanhaUnidadeRepository;
        this.unidadeSaudeRepository = unidadeSaudeRepository;
        this.tipoVacinaRepository = tipoVacinaRepository;
    }


    @Override
    public void cadastrarCampanha(CampanhaRequestDTO dto) {

        TipoVacina tipoVacina = buscarTipoVacina(dto.tipoVacinaId());
        List<UnidadeSaude> unidadeSaudes = buscarUnidadeSaude(dto.unidadesIds());

        Campanha campanha = campanhaMapper.toEntity(dto, tipoVacina);
        campanhaRepository.save(campanha);

        List<CampanhaUnidade> campanhaUnidades = associarUnidades(campanha, unidadeSaudes);

        campanhaUnidadeRepository.saveAll(campanhaUnidades);

    }

    @Override
    public List<CampanhaResponseDTO> listarCampanhas() {
        return campanhaRepository.findAll()
                .stream()
                .map(this::montarResponseCampanha)
                .toList();
    }

    private CampanhaResponseDTO montarResponseCampanha(Campanha campanha) {

        String tipoVacina = campanha.getTipoVacina().getNmVacina();

        List<String> unidades = campanhaUnidadeRepository
                .findByCampanha(campanha)
                .stream()
                .map(cu -> cu.getUnidadeSaude().getNmUnidade())
                .toList();

        return campanhaMapper.toResponse(campanha, tipoVacina, unidades);
    }


    private List<UnidadeSaude> buscarUnidadeSaude(List<Long> ids){

        List<UnidadeSaude> unidades = unidadeSaudeRepository.findAllById(ids);

        if(unidades.size() != ids.size()){
            throw new RuntimeException("Uma ou mais unidades não existem");
        }

        return unidades;
    }

    private TipoVacina buscarTipoVacina(Long tipoVacinaId) {
        return tipoVacinaRepository.findById(tipoVacinaId)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
    }

    private List<CampanhaUnidade> associarUnidades(Campanha campanha, List<UnidadeSaude> unidades) {
        return unidades.stream()
                .map(unidade -> CampanhaUnidade.of(campanha, unidade))
                .toList();
    }



}
