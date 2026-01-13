package com.api.locvac.service;

import com.api.locvac.dto.EstoqueVacinaRequestDTO;
import com.api.locvac.dto.VacinaPorUnidadeResponseDTO;

import java.util.List;

public interface EstoqueVacinaService {

    void cadastrarEstoque(EstoqueVacinaRequestDTO dto);

    void atualizarQuantidadeEstoque(EstoqueVacinaRequestDTO dto);

    void removerVacinaDaUnidade(Long vacinaId, Long unidadeId);

    List<VacinaPorUnidadeResponseDTO> listarVacinasPorUnidade(Long unidadeId);
}
