package com.api.locvac.repository;

import com.api.locvac.model.associacao.FaixaVacina;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.FaixaVacinaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaixaVacinaRepository extends JpaRepository<FaixaVacina, FaixaVacinaId> {

    List<FaixaVacina> findByVacinaId(Long idVacina);
}
