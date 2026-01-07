package com.api.locvac.repository;

import com.api.locvac.model.associacao.FaixaVacina;
import com.api.locvac.model.associacao.VacinaRestricao;
import com.api.locvac.model.id.FaixaVacinaId;
import com.api.locvac.model.id.VacinaRestricaoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRestricaoRepository extends JpaRepository<VacinaRestricao, VacinaRestricaoId> {
}
