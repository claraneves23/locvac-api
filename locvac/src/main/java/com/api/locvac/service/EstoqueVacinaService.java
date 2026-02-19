package com.api.locvac.service;

import com.api.locvac.dto.EstoqueVacinaRequestDTO;
import com.api.locvac.dto.VacinaPorUnidadeResponseDTO;

import java.util.List;

public interface EstoqueVacinaService {

    void cadastrarEstoque(EstoqueVacinaRequestDTO dto);

    void atualizarQuantidadeEstoque(Long vacinaId, Long unidadeId, Integer quantidade);

    void removerVacinaDaUnidade(Long vacinaId, Long unidadeId);

    List<VacinaPorUnidadeResponseDTO> listarVacinasPorUnidade(Long unidadeId);
}
