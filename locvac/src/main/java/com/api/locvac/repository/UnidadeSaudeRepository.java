package com.api.locvac.repository;

import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.model.core.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long> {

    boolean existsByNmUnidadeAndNmCepAndCdUnidadeNot(String nmUnidade, String nmCep, Long id);
    boolean existsById(Long unidadeId);

    Optional<UnidadeSaude> findUnidadeSaudeByNmUnidadeContainingIgnoreCase(String nmUnidade);
}

