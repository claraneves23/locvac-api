package com.api.locvac.repository;

import com.api.locvac.model.associacao.VacinaRestricao;
import com.api.locvac.model.core.Restricao;
import com.api.locvac.model.id.VacinaRestricaoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestricaoRepository extends JpaRepository<Restricao, Long> {
}
