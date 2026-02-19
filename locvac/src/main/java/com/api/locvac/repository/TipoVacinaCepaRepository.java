package com.api.locvac.repository;

import com.api.locvac.model.associacao.TipoVacinaCepa;
import com.api.locvac.model.associacao.TipoVacinaFaixa;
import com.api.locvac.model.id.TipoVacinaCepaId;
import com.api.locvac.model.id.TipoVacinaFaixaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoVacinaCepaRepository extends JpaRepository<TipoVacinaCepa, TipoVacinaCepaId> {
    @Query("""
    SELECT COUNT(tvc) > 0
    FROM TipoVacinaCepa tvc
    WHERE LOWER(tvc.tipoVacina.nmVacina) = LOWER(:nome)
      AND tvc.cepa.id = :cepaId
      AND (:tipoVacinaId IS NULL
           OR tvc.tipoVacina.cdTipoVacina <> :tipoVacinaId)
""")
    boolean existsByNomeAndCepa(
            @Param("nome") String nome,
            @Param("cepaId") Long cepaId,
            @Param("tipoVacinaId") Long tipoVacinaId
    );

}
