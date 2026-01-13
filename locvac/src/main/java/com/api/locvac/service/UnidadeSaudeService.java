package com.api.locvac.service;

import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.model.core.UnidadeSaude;

import java.util.List;

public interface UnidadeSaudeService {

    public void cadastrarUnidade(UnidadeSaudeRequestDTO dto);

    public List<UnidadeSaude> listarUnidade();
}
