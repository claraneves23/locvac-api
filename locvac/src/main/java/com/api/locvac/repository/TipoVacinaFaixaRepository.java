package com.api.locvac.repository;

import com.api.locvac.model.associacao.TipoVacinaFaixa;
import com.api.locvac.model.id.TipoVacinaFaixaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoVacinaFaixaRepository extends JpaRepository<TipoVacinaFaixa, TipoVacinaFaixaId> {
}
