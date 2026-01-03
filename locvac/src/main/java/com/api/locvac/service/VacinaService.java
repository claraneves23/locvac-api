package com.api.locvac.service;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.model.core.Vacina;

import java.util.List;

public interface VacinaService {

    public void cadastrarVacina(VacinaRequestDTO dto);

    public List<Vacina> listarVacinas();
}
