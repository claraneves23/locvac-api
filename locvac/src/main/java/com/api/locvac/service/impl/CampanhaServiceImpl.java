package com.api.locvac.service.impl;

import com.api.locvac.dto.CampanhaPatchDTO;
import com.api.locvac.dto.CampanhaRequestDTO;
import com.api.locvac.dto.CampanhaResponseDTO;
import com.api.locvac.mapper.CampanhaMapper;
import com.api.locvac.model.associacao.CampanhaUnidade;
import com.api.locvac.model.core.*;
import com.api.locvac.repository.*;
import com.api.locvac.service.CampanhaService;
import com.api.locvac.utils.ValidacaoPeriodoUtils;
import com.api.locvac.utils.AssociacaoUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        ValidacaoPeriodoUtils.validarDataFinalPosterior(dto.dataInicio(), dto.dataFim());
        validarCampanhaDuplicada(dto.nome(), tipoVacina, dto.dataInicio(), dto.dataFim(), null);
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

    @Override
    public void atualizarCampanha(Long campanhaId, CampanhaPatchDTO campanhaAtualizada) {
        Campanha campanha =  campanhaRepository.findById(campanhaId)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));

        if (campanhaAtualizada.nome() != null) {
            campanha.setNmCampanha(campanhaAtualizada.nome());
        }

        if (campanhaAtualizada.dataInicio() != null) {
            campanha.setDtComecoCampanha(campanhaAtualizada.dataInicio());
        }

        if (campanhaAtualizada.dataFim() != null) {
            campanha.setDtFimCampanha(campanhaAtualizada.dataFim());
        }

        if (campanhaAtualizada.tipoVacinaId() != null) {
            TipoVacina tipoVacina = buscarTipoVacina(campanhaAtualizada.tipoVacinaId());
            campanha.setTipoVacina(tipoVacina);
        }

        if (campanhaAtualizada.unidadesIds() != null) {
            AssociacaoUtils.atualizar(
                    campanha.getUnidades(),
                    campanhaAtualizada.unidadesIds(),
                    cu -> cu.getUnidadeSaude().getCdUnidade(),
                    unidadeSaudeRepository::findById,
                    unidade -> CampanhaUnidade.of(campanha, unidade)
            );
        }

        ValidacaoPeriodoUtils.validarDataFinalPosterior(
                campanha.getDtComecoCampanha(),
                campanha.getDtFimCampanha()
        );

        validarCampanhaDuplicada(
                campanha.getNmCampanha(),
                campanha.getTipoVacina(),
                campanha.getDtComecoCampanha(),
                campanha.getDtFimCampanha(),
                campanha.getCdCampanha()
        );

        campanhaRepository.save(campanha);
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

    //ver com o joão
    private void validarCampanhaDuplicada(String nmCampanha, TipoVacina tipoVacina, LocalDate dtComeco, LocalDate dtFim, Long campanhaId){
        boolean existe = campanhaRepository.existsByNmCampanhaAndTipoVacinaAndDtComecoCampanhaAndDtFimCampanhaAndCdCampanhaNot(
                nmCampanha, tipoVacina, dtComeco, dtFim, campanhaId);

        if (existe) {
            throw new RuntimeException("Vacina já cadastrada");
        }
    }


}
