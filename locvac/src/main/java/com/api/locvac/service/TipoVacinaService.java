package com.api.locvac.service;

import com.api.locvac.dto.TipoVacinaRequestDTO;
import com.api.locvac.dto.TipoVacinaResponseDTO;

import java.util.List;

public interface TipoVacinaService {

    void cadastrarTipoVacina(TipoVacinaRequestDTO dto);

    List<TipoVacinaResponseDTO> listarTiposVacina();


    TipoVacinaResponseDTO buscarPorId(Long id);
}
