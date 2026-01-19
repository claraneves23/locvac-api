package com.api.locvac.repository;

import com.api.locvac.model.associacao.CampanhaUnidade;
import com.api.locvac.model.core.Campanha;
import com.api.locvac.model.id.CampanhaUnidadeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CampanhaUnidadeRepository extends JpaRepository<CampanhaUnidade, CampanhaUnidadeId> {
    List<CampanhaUnidade> findByCampanha(Campanha campanha);
}
