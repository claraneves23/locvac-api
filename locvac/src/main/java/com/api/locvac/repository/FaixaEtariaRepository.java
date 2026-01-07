package com.api.locvac.repository;

import com.api.locvac.model.associacao.FaixaVacina;
import com.api.locvac.model.core.FaixaEtaria;
import com.api.locvac.model.id.FaixaVacinaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaixaEtariaRepository extends JpaRepository<FaixaEtaria, Long> {
}
