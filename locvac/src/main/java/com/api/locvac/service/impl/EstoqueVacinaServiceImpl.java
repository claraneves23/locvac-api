package com.api.locvac.service.impl;

import com.api.locvac.dto.EstoqueVacinaRequestDTO;
import com.api.locvac.dto.VacinaPorUnidadeResponseDTO;
import com.api.locvac.mapper.VacinaMapper;
import com.api.locvac.model.associacao.EstoqueVacina;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.EstoqueVacinaId;
import com.api.locvac.repository.EstoqueVacinaRepository;
import com.api.locvac.repository.UnidadeSaudeRepository;
import com.api.locvac.repository.VacinaRepository;
import com.api.locvac.service.EstoqueVacinaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EstoqueVacinaServiceImpl implements EstoqueVacinaService {

    //retirar as relacoes vacina faixa etaria e restricoes
    //testar de novo
    //
    private final EstoqueVacinaRepository estoqueVacinaRepository;
    private final VacinaRepository vacinaRepository;
    private final VacinaMapper vacinaMapper;
    private final UnidadeSaudeRepository unidadeSaudeRepository;

    public EstoqueVacinaServiceImpl(
            EstoqueVacinaRepository estoqueVacinaRepository,
            VacinaRepository vacinaRepository, VacinaMapper vacinaMapper,
            UnidadeSaudeRepository unidadeSaudeRepository
    ) {
        this.estoqueVacinaRepository = estoqueVacinaRepository;
        this.vacinaRepository = vacinaRepository;
        this.vacinaMapper = vacinaMapper;
        this.unidadeSaudeRepository = unidadeSaudeRepository;
    }


    @Override
    public void cadastrarEstoque(EstoqueVacinaRequestDTO dto) {

        Vacina vacina = buscarVacina(dto.vacinaId());

        UnidadeSaude unidadeSaude = buscarUnidadeSaude(dto.unidadeId());

        EstoqueVacinaId id = new EstoqueVacinaId(vacina.getId(), unidadeSaude.getCdUnidade());

        if (estoqueVacinaRepository.existsById(id)){
            throw new RuntimeException("Estoque já existe");
        }

        EstoqueVacina estoqueVacina = EstoqueVacina.of(unidadeSaude, vacina, dto.quantidade());

        estoqueVacinaRepository.save(estoqueVacina);

    }

    @Override
    public void atualizarQuantidadeEstoque(EstoqueVacinaRequestDTO dto) {

        Vacina vacina = buscarVacina(dto.vacinaId());

        UnidadeSaude unidadeSaude = buscarUnidadeSaude(dto.unidadeId());

        EstoqueVacinaId id = new EstoqueVacinaId(vacina.getId(), unidadeSaude.getCdUnidade());

        EstoqueVacina estoqueVacina = estoqueVacinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoqueVacina.setQuantidade(dto.quantidade());

        estoqueVacinaRepository.save(estoqueVacina);
    }

    @Override
    public void removerVacinaDaUnidade(Long vacinaId, Long unidadeId) {

        EstoqueVacinaId id = new EstoqueVacinaId(vacinaId, unidadeId);

        if (!estoqueVacinaRepository.existsById(id)) {
            throw new RuntimeException("Estoque não encontrado");
        }

        estoqueVacinaRepository.deleteById(id);
    }

    @Override
    public List<VacinaPorUnidadeResponseDTO> listarVacinasPorUnidade(Long unidadeId) {
        UnidadeSaude unidade = buscarUnidadeSaude(unidadeId);


        return estoqueVacinaRepository.findByUnidadeSaude(unidade)
                .stream()
                .map(vacinaMapper::toPorUnidade)
                .toList();
    }

    private Vacina buscarVacina(Long id){
        return vacinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
    }

    private UnidadeSaude buscarUnidadeSaude(Long id){
        return unidadeSaudeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
    }
}
