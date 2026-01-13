package com.api.locvac.service.impl;

import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.mapper.UnidadeSaudeMapper;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.repository.UnidadeSaudeRepository;
import com.api.locvac.service.UnidadeSaudeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {

    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final UnidadeSaudeMapper unidadeSaudeMapper;

    public UnidadeSaudeServiceImpl(UnidadeSaudeRepository unidadeSaudeRepository, UnidadeSaudeMapper unidadeSaudeMapper) {
        this.unidadeSaudeRepository = unidadeSaudeRepository;
        this.unidadeSaudeMapper = unidadeSaudeMapper;
    }

    @Override
    public void cadastrarUnidade(UnidadeSaudeRequestDTO dto) {
        validarUnidadeDuplicada(dto);
        UnidadeSaude unidadeSaude = salvarUnidadeSaude(dto);
    }

    @Override
    public List<UnidadeSaude> listarUnidade() {
        return unidadeSaudeRepository.findAll();
    }

    private void validarUnidadeDuplicada(UnidadeSaudeRequestDTO dto){
        boolean existe = unidadeSaudeRepository.existsByNmUnidadeIgnoreCaseAndNmCep(dto.nmUnidade(),
                dto.nmCep());

        if (existe) {
            throw new RuntimeException("Unidade Saude já cadastrada");
        }
    }

    private UnidadeSaude salvarUnidadeSaude(UnidadeSaudeRequestDTO dto){
        UnidadeSaude unidadeSaude = unidadeSaudeMapper.toEntity(dto);
        return unidadeSaudeRepository.save(unidadeSaude);

    }

}
