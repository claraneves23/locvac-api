package com.api.locvac.service;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.dto.VacinaResponseDTO;
import com.api.locvac.model.core.Vacina;

import java.util.List;

public interface VacinaService {

    public void cadastrarVacina(VacinaRequestDTO dto);

    void removerVacina(Long vacinaId);

    public List<VacinaResponseDTO> listarVacinas();
}
