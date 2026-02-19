package com.api.locvac.service;

import com.api.locvac.dto.VacinaPatchDTO;
import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;

import java.util.List;

public interface VacinaService {

    void cadastrarVacina(VacinaRequestDTO dto);

    void removerVacina(Long vacinaId);

    List<VacinaResponseDTO> listarVacinas();

    void atualizarVacina(Long vacinaId, VacinaPatchDTO vacinaAtualizada);
}
