package com.api.locvac.service;

import com.api.locvac.dto.CampanhaRequestDTO;
import com.api.locvac.dto.CampanhaResponseDTO;
import com.api.locvac.dto.EstoqueVacinaRequestDTO;

import java.util.List;

public interface CampanhaService {

    void cadastrarCampanha(CampanhaRequestDTO dto);

    List<CampanhaResponseDTO> listarCampanhas();
}
