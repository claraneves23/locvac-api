package com.api.locvac.service.impl;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.mapper.VacinaMapper;
import com.api.locvac.model.core.Fabricante;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.repository.FabricanteRepository;
import com.api.locvac.repository.VacinaRepository;
import com.api.locvac.service.VacinaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacinaServiceImpl implements VacinaService {

    private final VacinaRepository vacinaRepository;
    private final FabricanteRepository fabricanteRepository;
    private final VacinaMapper vacinaMapper;

    public VacinaServiceImpl(VacinaRepository vacinaRepository, FabricanteRepository fabricanteRepository, VacinaMapper vacinaMapper) {
        this.vacinaRepository = vacinaRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.vacinaMapper = vacinaMapper;
    }


    @Override
    public void cadastrarVacina(VacinaRequestDTO dto) {

        Fabricante fabricante = fabricanteRepository.findById(dto.getFabricanteId())
                .orElseThrow(() -> new RuntimeException("Fabricante não encontrado"));

        boolean existe = vacinaRepository
                .existsByNmVacinaIgnoreCaseAndFabricanteAndDtFabricacao(
                        dto.getNome(),
                        fabricante,
                        dto.getDtFabricacao()
                );

        if (existe) {
            throw new RuntimeException("Vacina já cadastrada");
        }

        Vacina vacina = vacinaMapper.toEntity(dto, fabricante);
        vacinaRepository.save(vacina);
    }

    @Override
    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();

    }


}
