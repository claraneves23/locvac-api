package com.api.locvac.service.impl;

import com.api.locvac.dto.CampanhaRequestDTO;
import com.api.locvac.dto.CampanhaResponseDTO;
import com.api.locvac.dto.TipoVacinaResponseDTO;
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
    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final TipoVacinaRepository tipoVacinaRepository;

    public CampanhaServiceImpl(CampanhaMapper campanhaMapper, CampanhaRepository campanhaRepository, UnidadeSaudeRepository unidadeSaudeRepository, TipoVacinaRepository tipoVacinaRepository) {
        this.campanhaMapper = campanhaMapper;
        this.campanhaRepository = campanhaRepository;
        this.unidadeSaudeRepository = unidadeSaudeRepository;
        this.tipoVacinaRepository = tipoVacinaRepository;
    }


    @Override
    public void cadastrarCampanha(CampanhaRequestDTO dto) {
        TipoVacina tipoVacina = buscarTipoVacina(dto.tipoVacinaId());
        List<UnidadeSaude> unidades = buscarUnidadeSaude(dto.unidadesIds());

        Campanha campanha = campanhaMapper.toEntity(dto, tipoVacina, unidades);

        campanhaRepository.save(campanha);

    }

    @Override
    public List<CampanhaResponseDTO> listarCampanhas() {
        return campanhaRepository.findAll()
                .stream()
                .map(campanhaMapper::toResponse)
                .toList();
    }

    @Override
    public Campanha filtrarPorNome(String nome) {
        return campanhaRepository.findCampanhaByNmCampanhaContainingIgnoreCase(nome)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));
    }

    @Override
    public Campanha buscarPorId(Long id) {
        return campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));
    }


    @Override
    public void removerCampanha(Long campanhaId){
        Campanha campanha = campanhaRepository.findById(campanhaId)
                .orElseThrow(() -> new RuntimeException("Campanha não existe"));

        campanhaRepository.delete(campanha);
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
