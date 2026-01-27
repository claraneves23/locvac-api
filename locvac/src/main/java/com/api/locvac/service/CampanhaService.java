package com.api.locvac.service;

import com.api.locvac.dto.CampanhaRequestDTO;
import com.api.locvac.dto.CampanhaResponseDTO;
import com.api.locvac.dto.EstoqueVacinaRequestDTO;
import com.api.locvac.dto.TipoVacinaResponseDTO;
import com.api.locvac.model.core.Campanha;

import java.util.List;

public interface CampanhaService {

    void cadastrarCampanha(CampanhaRequestDTO dto);

    List<CampanhaResponseDTO> listarCampanhas();

    Campanha filtrarPorNome(String nome);

    Campanha buscarPorId(Long id);

    void removerCampanha(Long campanhaId);
}
