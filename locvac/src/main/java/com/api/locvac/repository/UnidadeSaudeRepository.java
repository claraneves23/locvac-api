package com.api.locvac.repository;

import com.api.locvac.model.core.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long> {

    boolean existsByNmUnidadeIgnoreCaseAndNmCep(
            String nmUnidade,
            String nmCep);
}

