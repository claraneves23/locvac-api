package com.api.locvac.service;

import com.api.locvac.dto.UnidadeSaudePatchDTO;
import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.dto.UnidadeSaudeResponseDTO;
import com.api.locvac.model.core.UnidadeSaude;

import java.util.List;

public interface UnidadeSaudeService {

    void cadastrarUnidade(UnidadeSaudeRequestDTO dto);

    List<UnidadeSaudeResponseDTO> listarUnidade();

    UnidadeSaudeResponseDTO buscarPorId(Long id);

    UnidadeSaudeResponseDTO filtrarPorNome(String nmUnidade);

    void removerUnidadeSaude(Long unidadeSaudeId);

    void atualizarUnidade(Long unidadeId, UnidadeSaudePatchDTO unidadeAtualizada);
}
