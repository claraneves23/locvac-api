package com.api.locvac.repository;

import com.api.locvac.model.associacao.EstoqueVacina;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.model.id.EstoqueVacinaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueVacinaRepository extends JpaRepository<EstoqueVacina, EstoqueVacinaId> {
    List<EstoqueVacina> findByUnidadeSaude(UnidadeSaude unidade);

    boolean existsById(EstoqueVacinaId id);

    void deleteById(EstoqueVacinaId id);
}
