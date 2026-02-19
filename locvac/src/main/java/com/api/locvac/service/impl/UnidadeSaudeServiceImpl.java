package com.api.locvac.service.impl;

import com.api.locvac.dto.UnidadeSaudePatchDTO;
import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.dto.UnidadeSaudeResponseDTO;
import com.api.locvac.mapper.UnidadeSaudeMapper;
import com.api.locvac.mapper.patch.UnidadeSaudePatchMapper;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.repository.UnidadeSaudeRepository;
import com.api.locvac.service.UnidadeSaudeService;
import com.api.locvac.service.validation.CepService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {

    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final UnidadeSaudeMapper unidadeSaudeMapper;
    private final UnidadeSaudePatchMapper mapper;
    private final CepService cepService;

    public UnidadeSaudeServiceImpl(UnidadeSaudeRepository unidadeSaudeRepository, UnidadeSaudeMapper unidadeSaudeMapper, UnidadeSaudePatchMapper mapper, CepService cepService) {
        this.unidadeSaudeRepository = unidadeSaudeRepository;
        this.unidadeSaudeMapper = unidadeSaudeMapper;
        this.mapper = mapper;
        this.cepService = cepService;
    }

    @Override
    public void cadastrarUnidade(UnidadeSaudeRequestDTO dto) {
        cepService.validarCep(dto.nmCep());
        validarUnidadeDuplicada(dto.nmUnidade(), dto.nmCep(), null);
        UnidadeSaude unidadeSaude = salvarUnidadeSaude(dto);
    }

    @Override
    public List<UnidadeSaudeResponseDTO> listarUnidade() {
        return unidadeSaudeRepository.findAll()
                .stream()
                .map(unidadeSaudeMapper::toResponse)
                .toList();
    }

    @Override
    public UnidadeSaudeResponseDTO buscarPorId(Long id) {
        UnidadeSaude unidadeSaude = unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        return unidadeSaudeMapper.toResponse(unidadeSaude);
    }

    @Override
    public UnidadeSaudeResponseDTO filtrarPorNome(String nmUnidade) {
        UnidadeSaude unidadeSaude = unidadeSaudeRepository.findUnidadeSaudeByNmUnidadeContainingIgnoreCase(nmUnidade)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        return unidadeSaudeMapper.toResponse(unidadeSaude);
    }

    private void validarUnidadeDuplicada(String nmUnidade, String nmCep, Long id){
        boolean existe = unidadeSaudeRepository
                .existsByNmUnidadeAndNmCepAndCdUnidadeNot(nmUnidade, nmCep, id);

        if (existe) {
            throw new RuntimeException("Vacina já cadastrada");
        }
    }

    private UnidadeSaude salvarUnidadeSaude(UnidadeSaudeRequestDTO dto){
        UnidadeSaude unidadeSaude = unidadeSaudeMapper.toEntity(dto);
        return unidadeSaudeRepository.save(unidadeSaude);

    }

    @Override
    public void removerUnidadeSaude(Long unidadeSaudeId){

        if(!unidadeSaudeRepository.existsById(unidadeSaudeId)){
            throw new RuntimeException("Unidade Saude não existe");
        }

        unidadeSaudeRepository.deleteById(unidadeSaudeId);

    }

    @Override
    public void atualizarUnidade(Long unidadeId, UnidadeSaudePatchDTO unidadeAtualizada) {
        UnidadeSaude unidadeSaude =  unidadeSaudeRepository.findById(unidadeId)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        mapper.patch(unidadeAtualizada, unidadeSaude);

        cepService.validarCep(unidadeSaude.getNmCep());

        validarUnidadeDuplicada(
                unidadeSaude.getNmUnidade(),
                unidadeSaude.getNmCep(),
                unidadeId
        );

        unidadeSaudeRepository.save(unidadeSaude);
    }

}
