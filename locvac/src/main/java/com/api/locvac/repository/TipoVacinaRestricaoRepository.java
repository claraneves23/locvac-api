package com.api.locvac.repository;

import com.api.locvac.model.associacao.TipoVacinaFaixa;
import com.api.locvac.model.associacao.TipoVacinaRestricao;
import com.api.locvac.model.id.TipoVacinaFaixaId;
import com.api.locvac.model.id.TipoVacinaRestricaoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoVacinaRestricaoRepository extends JpaRepository<TipoVacinaRestricao, TipoVacinaRestricaoId> {
}
